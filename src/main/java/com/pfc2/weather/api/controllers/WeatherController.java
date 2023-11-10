package com.pfc2.weather.api.controllers;

import com.pfc2.weather.api.exceptions.ApiException;
import com.pfc2.weather.api.services.WeatherService;
import com.pfc2.weather.api.vos.BaseResponseVo;
import com.pfc2.weather.api.vos.ConditionRequestVo;
import com.pfc2.weather.api.vos.ConditionResponseVo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/weathers")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    @PostMapping(path = "/conditions")
    public ResponseEntity<BaseResponseVo<ConditionResponseVo>> findConditions(@RequestBody ConditionRequestVo conditionRequestVo){
        try {
            ConditionResponseVo result = weatherService.findConditions(conditionRequestVo);
            return ResponseEntity.ok(BaseResponseVo.<ConditionResponseVo>builder()
                            .statusCode(HttpStatus.OK.getReasonPhrase())
                            .data(result)
                    .build());
        } catch (ApiException ex) {
            throw new ApiException(ex.getHttpStatus(), ex.getMessages());
        }
    }

}
