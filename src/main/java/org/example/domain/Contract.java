package org.example.domain;

import org.springframework.security.core.GrantedAuthority;

public enum Contract implements GrantedAuthority {
    FIX, FACT;

    @Override
    public String getAuthority() {
        return name();
    }
}
