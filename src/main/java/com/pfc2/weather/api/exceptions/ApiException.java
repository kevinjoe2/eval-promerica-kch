package com.pfc2.weather.api.exceptions;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

/**
 * Custom exception api.
 * @author jchamorro
 * */
@RequiredArgsConstructor
@Getter
@Setter
public class ApiException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final List<String> messages;

}
