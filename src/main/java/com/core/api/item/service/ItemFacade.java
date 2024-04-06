package com.core.api.item.service;

import com.core.api.auth.AuthUser;
import com.core.api.item.repository.ItemCommentRepository;
import com.core.api.item.repository.ItemLikeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemFacade {
    private final ItemService itemService;
    private final ItemCommentRepository itemCommentRepository;
    private final ItemLikeRepository itemLikeRepository;

    @Transactional
    public void delete(AuthUser user, Long itemId) {
        var item = itemService.findItemByIdAndUid(itemId, user.getId());

        itemService.deleteById(item.getId());
        itemCommentRepository.deleteAllByItemId(item.getId());
        itemLikeRepository.deleteAllByItemId(item.getId());
    }
}
