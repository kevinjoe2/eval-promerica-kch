package com.pfc2.weather.api.vos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConditionResponseVo {

    private String weather;
    private BigDecimal tempMin;
    private BigDecimal tempMax;
    private BigDecimal humidity;

}
