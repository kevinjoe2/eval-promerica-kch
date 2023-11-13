package com.pfc2.weather.api.utils;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Constants used in api.
 * @author jchamorro
 * */
@Component
@Getter
public class ConstantUtil {

    private ConstantUtil() {}

    @Value("${application.open-weather.api-key}")
    private String openWeatherApiKey;

    @Value("${application.open-weather.uri}")
    private String openWeatherUri;

}
