package com.core.api.item.repository;

import com.core.api.item.entity.ItemCount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemCountRepository extends JpaRepository<ItemCount, Long> {
}
