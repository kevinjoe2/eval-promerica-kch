package com.pfc2.weather.api.services;

import com.pfc2.weather.api.entities.WeatherHistoryEntity;
import com.pfc2.weather.api.repositories.WeatherRepository;
import com.pfc2.weather.api.utils.ConstantUtil;
import com.pfc2.weather.api.vos.ConditionRequestVo;
import com.pfc2.weather.api.vos.ConditionResponseVo;
import com.pfc2.weather.api.vos.WeatherApiResponseVo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Test for WeatherService
 * @author jchamorro
 * */
@ExtendWith(MockitoExtension.class)
class WeatherServiceTest {

    @Mock
    private WeatherRepository weatherRepository;

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ConstantUtil constantUtil;

    @InjectMocks
    private WeatherService service;

    @Test
    void findConditions() {
        ConditionRequestVo conditionRequestVo = ConditionRequestVo.builder()
                .lat(BigDecimal.valueOf(1.2568))
                .lon(BigDecimal.valueOf(15.2008))
                .build();
        Optional<WeatherHistoryEntity> opt1 = Optional.empty();
        WeatherHistoryEntity ent1 = WeatherHistoryEntity.builder().build();
        WeatherApiResponseVo wea1 = getWeatherApiResponseVo();
        ResponseEntity<WeatherApiResponseVo> resp1 = ResponseEntity.ok(wea1);
        Mockito.when(weatherRepository.findByLatAndLon(Mockito.any(BigDecimal.class), Mockito.any(BigDecimal.class)))
                .thenReturn(opt1);
        Mockito.when(weatherRepository.save(Mockito.any(WeatherHistoryEntity.class))).thenReturn(ent1);
        Mockito.when(restTemplate.getForEntity(Mockito.anyString(), Mockito.eq(WeatherApiResponseVo.class)))
                .thenReturn(resp1);
        Mockito.when(constantUtil.getOpenWeatherApiKey()).thenReturn("ASdasdasd");
        Mockito.when(constantUtil.getOpenWeatherUri()).thenReturn("http:ASD");
        ConditionResponseVo result = service.findConditions(conditionRequestVo);
        Assertions.assertNotNull(result);
    }

    @Test
    void findConditionsp1() {
        ConditionRequestVo conditionRequestVo = ConditionRequestVo.builder()
                .lat(BigDecimal.valueOf(1.2568))
                .lon(BigDecimal.valueOf(15.2008))
                .build();
        WeatherHistoryEntity wea1 = new WeatherHistoryEntity();
        wea1.setId("asd");
        wea1.setCreated(LocalDateTime.now());
        Optional<WeatherHistoryEntity> opt1 = Optional.of(wea1);
        Mockito.when(weatherRepository.findByLatAndLon(Mockito.any(BigDecimal.class), Mockito.any(BigDecimal.class)))
                .thenReturn(opt1);
        ConditionResponseVo result = service.findConditions(conditionRequestVo);
        Assertions.assertNotNull(result);
    }

    private static WeatherApiResponseVo getWeatherApiResponseVo() {
        WeatherApiResponseVo wea1 = new WeatherApiResponseVo();
        WeatherApiResponseVo.Weather wea2 = new WeatherApiResponseVo.Weather();
        wea2.setMain("ASD");
        WeatherApiResponseVo.Main main1 = new WeatherApiResponseVo.Main();
        main1.setTemp_max(111.0);
        main1.setTemp_min(111.0);
        main1.setHumidity(11);
        wea1.setMain(main1);
        WeatherApiResponseVo.Coord coord1 = new WeatherApiResponseVo.Coord();
        coord1.setLat(11.000);
        coord1.setLon(121.000);
        wea1.setCoord(coord1);
        wea1.setWeather(List.of(wea2));
        return wea1;
    }

    @Test
    void findWeatherHistory() {
        List<WeatherHistoryEntity> list1 = new ArrayList<>();
        WeatherHistoryEntity wea1 = new WeatherHistoryEntity();
        wea1.setCreated(LocalDateTime.now());
        wea1.setId("asd");
        list1.add(wea1);
        Mockito.when(weatherRepository.findAll()).thenReturn(list1);
        List<ConditionResponseVo> result = service.findWeatherHistory();
        Assertions.assertFalse(result.isEmpty());
    }
}