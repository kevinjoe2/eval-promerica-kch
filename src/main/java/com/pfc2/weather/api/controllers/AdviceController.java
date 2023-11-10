package com.pfc2.weather.api.controllers;

import com.pfc2.weather.api.exceptions.ApiException;
import com.pfc2.weather.api.vos.ErrorVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.Objects;

@RestControllerAdvice
@Slf4j
public class AdviceController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<ErrorVo> handleApiException(ApiException apiException) {
        log.error("*** AdviceController.handleApiException", apiException);
        return ResponseEntity.badRequest().body(ErrorVo.builder()
                .code(apiException.getHttpStatus().getReasonPhrase())
                .errors(apiException.getMessages())
                .build());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorVo> handleExpiredJwtException(RuntimeException runtimeException) {
        log.error("*** AdviceController.handleExpiredJwtException", runtimeException);
        if (runtimeException.getCause() instanceof ApiException apiException) {
            return ResponseEntity.badRequest().body(ErrorVo.builder()
                    .code(apiException.getHttpStatus().getReasonPhrase())
                    .errors(apiException.getMessages())
                    .build());
        }
        String messageException = Objects.nonNull(runtimeException.getMessage()) ? runtimeException.getMessage() : "";
        return ResponseEntity.badRequest().body(ErrorVo.builder()
                .code(HttpStatus.SERVICE_UNAVAILABLE.getReasonPhrase())
                .errors(List.of(messageException))
                .build());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorVo> handleException(Exception exception) {
        log.error("*** AdviceController.handleException", exception);
        if (exception.getCause() instanceof ApiException apiException) {
            return ResponseEntity.badRequest().body(ErrorVo.builder()
                    .code(apiException.getHttpStatus().getReasonPhrase())
                    .errors(apiException.getMessages())
                    .build());
        }
        String messageException = Objects.nonNull(exception.getMessage()) ? exception.getMessage() : "";
        return ResponseEntity.badRequest().body(ErrorVo.builder()
                .code(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .errors(List.of(messageException))
                .build());
    }

}
