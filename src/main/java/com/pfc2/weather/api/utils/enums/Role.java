package com.pfc2.weather.api.utils.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Getter
public enum Role {

    USER(List.of(Permission.ADMIN_READ)),
    ADMIN(Arrays.asList(Permission.ADMIN_READ,Permission.ADMIN_UPDATE,Permission.ADMIN_CREATE,Permission.ADMIN_DELETE));

    private final List<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getValue()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
