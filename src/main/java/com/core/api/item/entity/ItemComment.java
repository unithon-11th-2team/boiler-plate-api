package com.core.api.item.entity;

import com.core.api.item.dto.request.CommentSaveDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "item_comment")
public class ItemComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "uid")
    private Long uId;

    @Column(name = "message")
    private String message;

    public ItemComment(Long uid, CommentSaveDto commentSaverDto) {
        this.uId = uid;
        this.itemId = commentSaverDto.getItemId();
        this.message = commentSaverDto.getMessage();
    }
}
