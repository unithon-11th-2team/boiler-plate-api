package com.core.api.item.dto.response;

import lombok.Data;

@Data
public class ItemDetailCommentLikeDto {
    private Long uid;
    private Long likeId;
    private String nickname;
}
