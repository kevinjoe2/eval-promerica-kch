package com.pfc2.weather.api.controllers;

import com.pfc2.weather.api.exceptions.ApiException;
import com.pfc2.weather.api.services.WeatherService;
import com.pfc2.weather.api.vos.BaseResponseVo;
import com.pfc2.weather.api.vos.ConditionRequestVo;
import com.pfc2.weather.api.vos.ConditionResponseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * Controller for obtain weather.
 * @author jchamorro
 * */
@RestController
@RequestMapping(path = "/api/v1/weather")
@Validated
@RequiredArgsConstructor
@Slf4j
public class WeatherController {

    private final WeatherService weatherService;

    @PostMapping
    @Operation(summary = "Obtains weather information based on latitude and longitude.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the weather",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConditionResponseVo.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid parameters",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content) })
    public ResponseEntity<BaseResponseVo<ConditionResponseVo>> find(
            @RequestBody @Valid ConditionRequestVo conditionRequestVo){
        log.info("*** WeatherController.findConditions {}", conditionRequestVo);
        this.findValidate(conditionRequestVo);
        ConditionResponseVo result = weatherService.findConditions(conditionRequestVo);
        return ResponseEntity.ok(BaseResponseVo.<ConditionResponseVo>builder()
                .statusCode(HttpStatus.OK.getReasonPhrase())
                .data(result).build());
    }

    private void findValidate(ConditionRequestVo conditionRequestVo){
        if (conditionRequestVo.getLat().compareTo(BigDecimal.valueOf(-90)) < 0) {
            throw new ApiException(HttpStatus.BAD_REQUEST, List.of("Latitude cannot be less than -90."));
        }
        if (conditionRequestVo.getLon().compareTo(BigDecimal.valueOf(-180)) < 0) {
            throw new ApiException(HttpStatus.BAD_REQUEST, List.of("Length cannot be less than -180."));
        }
        if (conditionRequestVo.getLat().compareTo(BigDecimal.valueOf(90)) > 0) {
            throw new ApiException(HttpStatus.BAD_REQUEST, List.of("Latitude cannot be greater than 90."));
        }
        if (conditionRequestVo.getLon().compareTo(BigDecimal.valueOf(180)) > 0) {
            throw new ApiException(HttpStatus.BAD_REQUEST, List.of("Length cannot be greater than 180."));
        }
    }

    @GetMapping(path = "history")
    @PostMapping
    @Operation(summary = "Obtains history of all the climates consulted.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the weather history",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ConditionResponseVo.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid parameters",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content) })
    public ResponseEntity<BaseResponseVo<ConditionResponseVo>> findHistory(){
        log.info("*** WeatherController.findHistory");
        List<ConditionResponseVo> result = weatherService.findWeatherHistory();
        return ResponseEntity.ok(BaseResponseVo.<ConditionResponseVo>builder()
                .statusCode(HttpStatus.OK.getReasonPhrase())
                .dataList(result).build());
    }

}
