package org.example.domain;


import org.springframework.security.core.GrantedAuthority;

public enum TypeAccess implements GrantedAuthority {
ADMIN, DATABASES, FTP, SSH;

    @Override
    public String getAuthority() {
        return name();
    }
}
