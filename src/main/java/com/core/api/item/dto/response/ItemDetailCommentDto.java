package com.core.api.item.dto.response;

import lombok.Data;

@Data
public class ItemDetailCommentDto {
    private Long uid;
    private String nickname;
    private String message;
    private int likeCount;
    public ItemDetailCommentDto(Long uid, String nickname, String message, Long likeCount) {
        this.uid = uid;
        this.nickname = nickname;
        this.message = message;
        this.likeCount = likeCount == null ? 0 : likeCount.intValue();
    }
}
