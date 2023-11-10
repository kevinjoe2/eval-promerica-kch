package com.pfc2.weather.api.services;

import com.pfc2.weather.api.entities.WeatherHistoryEntity;
import com.pfc2.weather.api.exceptions.ApiException;
import com.pfc2.weather.api.repositories.WeatherRepository;
import com.pfc2.weather.api.vos.ConditionRequestVo;
import com.pfc2.weather.api.vos.ConditionResponseVo;
import com.pfc2.weather.api.vos.WeatherApiResponseVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WeatherService {

    private final WeatherRepository weatherRepository;

    private final RestTemplate restTemplate;

    @Value("${application.open-weather.api-key}")
    private String openWeatherApiKey;

    @Value("${application.open-weather.uri}")
    private String openWeatherUri;

    public ConditionResponseVo findConditions(ConditionRequestVo conditionRequestVo) {
        log.info("** WeatherService.findConditions {}", conditionRequestVo);
        Optional<WeatherHistoryEntity> weatherEntity = weatherRepository.findByLatAndLon(conditionRequestVo.getLat(), conditionRequestVo.getLon());
        LocalDateTime currentDateTimeMinus10Minutes = LocalDateTime.now().minusMinutes(10);
        if (weatherEntity.isPresent() && weatherEntity.get().getCreated().isAfter(currentDateTimeMinus10Minutes)) {
            log.info("** WeatherService.findConditions find weather in data base available");
            return mapToConditionResponseVo(weatherEntity.get());
        }
        WeatherApiResponseVo result = findWeatherByLatAndLon(conditionRequestVo);
        WeatherHistoryEntity entity = mapToWeatherHistoryEntity(result);
        log.info("** WeatherService.findConditions saving weather in data base");
        WeatherHistoryEntity entitySaved = weatherRepository.save(entity);
        log.info("** WeatherService.findConditions mapping to vo");
        return mapToConditionResponseVo(entitySaved);
    }

    private WeatherApiResponseVo findWeatherByLatAndLon(ConditionRequestVo conditionRequestVo){
        log.info("** WeatherService.findWeatherByLatAndLon {}", conditionRequestVo);
        ResponseEntity<WeatherApiResponseVo> response;
        try {
            log.info("** WeatherService.findWeatherByLatAndLon connecting with open weather");
            response = restTemplate.getForEntity(
                    String.format(openWeatherUri, conditionRequestVo.getLat(), conditionRequestVo.getLon(), openWeatherApiKey),
                    WeatherApiResponseVo.class
            );
        } catch (Exception ex) {
            log.error("Could not connect with open weather", ex);
            throw new ApiException(HttpStatus.SERVICE_UNAVAILABLE, List.of("Could not connect with open weather."));
        }
        if (response.getStatusCode().equals(HttpStatus.OK) && Objects.nonNull(response.getBody())) {
            log.info("** WeatherService.findWeatherByLatAndLon Success");
            return response.getBody();
        }
        log.error("Open weather's response is invalid {}", response);
        throw new ApiException(HttpStatus.valueOf(response.getStatusCode().value()), List.of("Open weather's response is invalid"));
    }

    private WeatherHistoryEntity mapToWeatherHistoryEntity(WeatherApiResponseVo apiResponseVo){
        log.info("** WeatherService.mapToWeatherHistoryEntity {}", apiResponseVo);
        if (Objects.nonNull(apiResponseVo)) {
            String weather = apiResponseVo.getWeather().stream()
                    .map(WeatherApiResponseVo.Weather::getMain)
                    .collect(Collectors.joining());
            return WeatherHistoryEntity.builder()
                    .created(LocalDateTime.now())
                    .weather(weather)
                    .tempMax(BigDecimal.valueOf(apiResponseVo.getMain().getTemp_max()))
                    .tempMin(BigDecimal.valueOf(apiResponseVo.getMain().getTemp_min()))
                    .humidity(BigDecimal.valueOf(apiResponseVo.getMain().getHumidity()))
                    .lat(BigDecimal.valueOf(apiResponseVo.getCoord().getLat()))
                    .lon(BigDecimal.valueOf(apiResponseVo.getCoord().getLon()))
                    .build();
        }
        log.error("Error mapToWeatherHistoryEntity");
        throw new ApiException(HttpStatus.SERVICE_UNAVAILABLE, List.of("Error mapToWeatherHistoryEntity"));
    }

    private ConditionResponseVo mapToConditionResponseVo(WeatherHistoryEntity entity){
        log.info("** WeatherService.mapToConditionResponseVo {}", entity);
        return ConditionResponseVo.builder()
                .weather(entity.getWeather())
                .tempMin(entity.getTempMin())
                .tempMax(entity.getTempMax())
                .humidity(entity.getHumidity())
                .build();
    }

    public List<ConditionResponseVo> findWeatherHistory(){
        log.info("*** WeatherService.findWeatherHistory");
        List<WeatherHistoryEntity> result = weatherRepository.findAll();
        return result.stream().map(this::mapToConditionResponseVo).toList();
    }
}
