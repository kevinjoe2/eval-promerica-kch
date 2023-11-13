package com.pfc2.weather.api.controllers;

import com.pfc2.weather.api.vos.BaseResponseVo;
import com.pfc2.weather.api.vos.ErrorVo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("errors")
public class ErrorController {

    @GetMapping(path = "access-denied")
    public ResponseEntity<BaseResponseVo<ErrorVo>> accessDenied() {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(BaseResponseVo.<ErrorVo>builder()
                        .data(ErrorVo.builder()
                                .code(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                                .errors(List.of("Unauthorized resource, requires authentication."))
                                .build())
                        .build());
    }

}
