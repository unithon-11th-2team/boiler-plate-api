package com.core.api.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserInfoResponse {
    /**
     * user id
     */
    private Long id;
    /**
     * nickname
     */
    private String nickname;
}
