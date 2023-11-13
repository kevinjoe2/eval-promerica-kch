package com.pfc2.weather.api.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfc2.weather.api.vos.ErrorVo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.List;

/**
 * Allow to have a custom config for handle exception routes.
 * @author jchamorro
 * */
@Slf4j
public class AuthenticationEntryPointConfig implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException exception
    ) throws IOException, ServletException {
        log.error("Error AuthenticationEntryPointConfig", exception);
        ErrorVo errorVo = ErrorVo.builder()
                .code(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase())
                .errors(List.of("Unexpected error"))
                .build();
        if (response.getStatus() == 404) {
            errorVo = ErrorVo.builder()
                    .code(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .errors(List.of("Resource not found."))
                    .build();
        } else if (exception.getMessage().equals("Full authentication is required to access this resource")) {
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            errorVo = ErrorVo.builder()
                    .code(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                    .errors(List.of("Unauthorized resource, requires authentication."))
                    .build();
        }
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().println(objectMapper.writeValueAsString(errorVo));
    }
}