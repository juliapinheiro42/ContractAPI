package com.contactpro.contactpro.model;

import org.springframework.security.core.GrantedAuthority;

public enum Profile implements GrantedAuthority {
    ADMIN, OPERADOR, LEITOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
