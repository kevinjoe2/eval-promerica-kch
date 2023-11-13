package com.pfc2.weather.api.vos;

import com.pfc2.weather.api.utils.enums.Role;
import lombok.Builder;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class RegisterRequestVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1905122041950251208L;

    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
}
