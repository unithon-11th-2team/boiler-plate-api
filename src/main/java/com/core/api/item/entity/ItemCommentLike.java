package com.core.api.item.entity;

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
@Table(name = "item_comment_like")
public class ItemCommentLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "item_comment_id")
    private Long itemCommentId;

    @Column(name = "uid")
    private Long uId;

    public ItemCommentLike(Long id, Long itemCommentId) {
        this.uId = id;
        this.itemCommentId = itemCommentId;
    }
}
