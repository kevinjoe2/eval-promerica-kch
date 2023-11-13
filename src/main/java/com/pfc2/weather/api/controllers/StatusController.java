package com.pfc2.weather.api.controllers;

import com.pfc2.weather.api.vos.BaseResponseVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sys")
@Slf4j
public class StatusController {

    @GetMapping(path = "status")
    public ResponseEntity<BaseResponseVo<String>> status(){
        return ResponseEntity.ok(BaseResponseVo.<String>builder()
                        .statusCode(HttpStatus.OK.getReasonPhrase()).build());
    }

}
