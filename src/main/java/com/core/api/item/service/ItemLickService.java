package com.core.api.item.service;

import com.core.api.item.repository.ItemLikeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemLickService {
    private final ItemLikeRepository itemLikeRepository;

    public void deleteAllByItemId(Long itemId) {
        itemLikeRepository.deleteAllByItemId(itemId);
    }
}
