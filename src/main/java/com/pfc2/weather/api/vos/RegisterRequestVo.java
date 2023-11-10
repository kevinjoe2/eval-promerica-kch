package com.pfc2.weather.api.vos;

import com.pfc2.weather.api.entities.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestVo {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private Role role;
}
