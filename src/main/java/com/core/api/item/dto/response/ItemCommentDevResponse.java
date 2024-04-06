package com.core.api.item.dto.response;

import com.core.api.item.entity.ItemComment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemCommentDevResponse {
    private Long id;
    private Long itemId;
    private Long uid;
    private String message;

    public static ItemCommentDevResponse from(ItemComment itemComment) {
        return new ItemCommentDevResponse(
                itemComment.getId(),
                itemComment.getItemId(),
                itemComment.getUid(),
                itemComment.getMessage()
        );
    }
}
