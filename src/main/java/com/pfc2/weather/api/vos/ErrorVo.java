package com.pfc2.weather.api.vos;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ErrorVo {
    private String code;
    private List<String> errors;
}
