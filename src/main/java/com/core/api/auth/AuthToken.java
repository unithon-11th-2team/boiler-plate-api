package com.core.api.auth;

import lombok.Data;

import static com.core.api.auth.AuthConstants.AUTH_TOKEN_HEADER_KEY;

@Data
public class AuthToken {
    private final String key;
    private final String token;

    public AuthToken(String token) {
        this.key = AUTH_TOKEN_HEADER_KEY;
        this.token = token;
    }
}

