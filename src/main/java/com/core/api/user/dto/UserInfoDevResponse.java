package com.core.api.user.dto;

import com.core.api.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UserInfoDevResponse {
    private Long id;
    private String nickname;
    private String deviceId;
    private String token;

    public static UserInfoDevResponse from(User user) {
        return new UserInfoDevResponse(
                user.getId(),
                user.getNickname(),
                user.getDeviceId(),
                user.getToken()
        );
    }
}
