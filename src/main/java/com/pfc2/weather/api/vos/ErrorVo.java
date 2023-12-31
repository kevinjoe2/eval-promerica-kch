package com.pfc2.weather.api.vos;

import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

/**
 * Vo generic for ErrorVo
 * @author jchamorro
 * */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1905122041950251208L;
    private String code;
    private List<String> errors;
}
