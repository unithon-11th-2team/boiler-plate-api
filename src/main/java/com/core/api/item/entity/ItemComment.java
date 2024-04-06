package com.core.api.item.entity;

import com.core.api.item.dto.request.CommentSaveDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Long uid;

    @Column(name = "message", columnDefinition = "varchar(512)")
    private String message;

    public ItemComment(Long uid, CommentSaveDto commentSaverDto) {
        this.uid = uid;
        this.itemId = commentSaverDto.getItemId();
        this.message = commentSaverDto.getMessage();
    }
}
