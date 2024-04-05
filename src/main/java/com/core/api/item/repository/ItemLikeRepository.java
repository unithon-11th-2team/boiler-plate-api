package com.core.api.item.repository;

import com.core.api.item.entity.ItemLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemLikeRepository extends JpaRepository<ItemLike, Long> {
    Long countByItemId(Long itemId);
}
