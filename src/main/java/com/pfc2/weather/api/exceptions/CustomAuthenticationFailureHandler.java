package com.pfc2.weather.api.exceptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pfc2.weather.api.vos.ErrorVo;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import java.io.IOException;
import java.util.List;

public class CustomAuthenticationFailureHandler implements AuthenticationEntryPoint {

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void commence(
            HttpServletRequest request,
            HttpServletResponse response,
            AuthenticationException authException
    ) throws IOException, ServletException {
        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
        ErrorVo errorVo = ErrorVo.builder()
                .code(httpStatus.getReasonPhrase())
                .errors(List.of(authException.getMessage()))
                .build();
        response.setStatus(httpStatus.value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getOutputStream().println(objectMapper.writeValueAsString(errorVo));
    }
}