package com.core.api.item.dto.response;

import com.core.api.item.entity.ItemComment;
import com.core.api.user.entity.User;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentSaveResponseDto {
    private Long id;
    private Long itemId;
    private String nickname;
    private String message;

    public CommentSaveResponseDto(User user, ItemComment itemComment) {
        this.id = itemComment.getId();
        this.itemId = itemComment.getItemId();
        this.nickname = user.getNickname();
        this.message = itemComment.getMessage();
    }
}
