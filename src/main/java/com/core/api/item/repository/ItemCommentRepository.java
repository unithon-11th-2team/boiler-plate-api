package com.core.api.item.repository;

import com.core.api.item.entity.ItemComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemCommentRepository extends JpaRepository<ItemComment, Long> {
    Long countByItemId(Long itemId);
}
