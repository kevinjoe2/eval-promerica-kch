package com.pfc2.weather.api.vos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class AuthenticationRequestVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1905122041950251208L;

    private String email;
    private String password;
}
