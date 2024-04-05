package com.core.api.auth;

import lombok.Data;

@Data
public class AuthUserImpl implements AuthUser {
    private final Long id;
    private final String nickname;

    @Override
    public Long getId() {
        return this.id;
    }
}
