package com.pfc2.weather.api.utils.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    USER_READ("management:read"),
    USER_UPDATE("management:update"),
    USER_CREATE("management:create"),
    USER_DELETE("management:delete");

    private final String value;

}
