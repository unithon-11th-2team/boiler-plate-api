package com.core.api.item.repository;

import com.core.api.item.entity.Item;
import com.core.api.item.entity.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAllByTypeIn(List<ItemType> type);

    List<Item> findAllByUid(Long uid);
}
