package com.core.api.item.dto.request;

import lombok.Data;

@Data
public class CommentSaveDto {
    private Long itemId;
    private String message;
}
