package com.core.api.item.service;

import com.core.api.item.repository.ItemCommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemCommentService {
    private final ItemCommentRepository itemCommentRepository;

    public void deleteAllByItemId(Long itemId) {
        itemCommentRepository.deleteAllByItemId(itemId);
    }
}
