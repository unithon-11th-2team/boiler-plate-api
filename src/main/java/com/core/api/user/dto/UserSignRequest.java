package com.core.api.user.dto;

import lombok.Data;

@Data
public class UserSignRequest {
    private String nickname;
    private String deviceId;
}
