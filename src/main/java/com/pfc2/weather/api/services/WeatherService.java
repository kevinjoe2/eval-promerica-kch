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

    @Value("${application.open-weather.api-key}")
    private String openWeatherUri;

    public ConditionResponseVo findConditions(ConditionRequestVo conditionRequestVo) {
        Optional<WeatherHistoryEntity> weatherEntity = weatherRepository.findByLatAndLon(conditionRequestVo.getLat(), conditionRequestVo.getLon());
        LocalDateTime currentDateTimeMinus10Minutes = LocalDateTime.now().minusMinutes(10);
        if (weatherEntity.isPresent() && weatherEntity.get().getCreated().isAfter(currentDateTimeMinus10Minutes)) {
            return mapToConditionResponseVo(weatherEntity.get());
        }

        WeatherApiResponseVo result = findWeatherByLatAndLon(conditionRequestVo);
        WeatherHistoryEntity entity = mapToWeatherHistoryEntity(result);
        WeatherHistoryEntity entitySaved = weatherRepository.save(entity);
        return mapToConditionResponseVo(entitySaved);
    }

    private WeatherApiResponseVo findWeatherByLatAndLon(ConditionRequestVo conditionRequestVo){
        try {
            ResponseEntity<WeatherApiResponseVo> response = restTemplate.getForEntity(
                    String.format(openWeatherUri, conditionRequestVo.getLat(), conditionRequestVo.getLon(), openWeatherApiKey),
                    WeatherApiResponseVo.class
            );
            if (response.getStatusCode().equals(HttpStatus.OK) && Objects.nonNull(response.getBody())) {
                return response.getBody();
            }
            throw new ApiException(HttpStatus.valueOf(response.getStatusCode().value()), List.of("Unexpected exception"));
        } catch (Exception ex) {
            throw new ApiException(HttpStatus.SERVICE_UNAVAILABLE, List.of("Api not found coordinates"));
        }
    }

    private WeatherHistoryEntity mapToWeatherHistoryEntity(WeatherApiResponseVo apiResponseVo){
        if (Objects.nonNull(apiResponseVo)) {
            String weather = apiResponseVo.getWeather().stream()
                    .map(WeatherApiResponseVo.Weather::getMain)
                    .collect(Collectors.joining());
            WeatherHistoryEntity entity = new WeatherHistoryEntity();
            entity.setCreated(LocalDateTime.now());
            entity.setWeather(weather);
            entity.setTempMax(BigDecimal.valueOf(apiResponseVo.getMain().getTemp_max()));
            entity.setTempMin(BigDecimal.valueOf(apiResponseVo.getMain().getTemp_min()));
            entity.setHumidity(BigDecimal.valueOf(apiResponseVo.getMain().getHumidity()));
            entity.setLat(BigDecimal.valueOf(apiResponseVo.getCoord().getLat()));
            entity.setLon(BigDecimal.valueOf(apiResponseVo.getCoord().getLon()));
            return entity;
        }
        throw new ApiException(HttpStatus.SERVICE_UNAVAILABLE, List.of("Error mapToWeatherHistoryEntity"));
    }

    private ConditionResponseVo mapToConditionResponseVo(WeatherHistoryEntity entity){
        return ConditionResponseVo.builder()
                .weather(entity.getWeather())
                .tempMin(entity.getTempMin())
                .tempMax(entity.getTempMax())
                .humidity(entity.getHumidity())
                .build();
    }
}
