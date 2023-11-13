package com.pfc2.weather.api.vos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Vo for ConditionResponse
 * @author jchamorro
 * */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ConditionResponseVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1905122041950251208L;

    private String weather;
    private BigDecimal tempMin;
    private BigDecimal tempMax;
    private BigDecimal humidity;

}
