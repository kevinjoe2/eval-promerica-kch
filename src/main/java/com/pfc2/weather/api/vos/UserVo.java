package com.pfc2.weather.api.vos;

import com.pfc2.weather.api.entities.enums.Role;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Data
@Builder
public class UserVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1905122041950251208L;

    private String email;
    private String password;
    private Role role;

}
