package com.neohack.backend.model;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    ROLE_ADMIN,
    ROLE_USER,
    ROLE_STUDENT,
    ROLE_TEACHER;

    @Override
    public String getAuthority() {
        return name();
    }
}
