package com.core.api.item.repository;

import com.core.api.item.entity.ItemCommentLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCommentLikeRepository extends JpaRepository<ItemCommentLike, Long> {
}
