package com.pfc2.weather.api.vos;

import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class BaseResponseVo<T extends Serializable> implements Serializable {

    @Serial
    private static final long serialVersionUID = 1905122041950251208L;

    private String statusCode;
    private T data;

}
