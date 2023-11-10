package com.pfc2.weather.api.controllers;

import com.pfc2.weather.api.services.WeatherService;
import com.pfc2.weather.api.vos.ConditionRequestVo;
import com.pfc2.weather.api.vos.ConditionResponseVo;
import lombok.RequiredArgsConstructor;
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

    @PostMapping(path = "conditions")
    public ResponseEntity<ConditionResponseVo> findConditions(@RequestBody ConditionRequestVo conditionRequestVo){
        try {
            ConditionResponseVo result = weatherService.findConditions(conditionRequestVo);
            return ResponseEntity.ok(result);
        } catch (Exception ex) {
            return ResponseEntity.badRequest().build();
        }
    }

}
