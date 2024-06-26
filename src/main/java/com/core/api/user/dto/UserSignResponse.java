package com.core.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserSignResponse {
    private Long id;
    private String nickname;
    private String token;
}
