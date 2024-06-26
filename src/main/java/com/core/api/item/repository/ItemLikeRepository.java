package com.core.api.item.repository;

import com.core.api.item.entity.ItemComment;
import com.core.api.item.entity.ItemLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemLikeRepository extends JpaRepository<ItemLike, Long> {
    Long countByItemId(Long itemId);

    void deleteAllByItemId(Long id);

    List<ItemLike> findAllByIdIn(List<Long> id);
}
