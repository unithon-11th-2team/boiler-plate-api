package com.core.api.item.dto.response;

import lombok.Data;

@Data
public class ItemDetailCommentDto {
    private Long uid;
    private String nickname;
    private String message;
}
