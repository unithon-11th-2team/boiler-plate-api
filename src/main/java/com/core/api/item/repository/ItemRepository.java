package com.core.api.item.repository;

import com.core.api.item.entity.Item;
import com.core.api.item.entity.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByTypeIn(List<ItemType> type);

    List<Item> findAllByUid(Long uid);

    List<Item> findAllByIdIn(List<Long> id);

    Optional<Item> findByIdAndUid(Long id, Long uid);
}
