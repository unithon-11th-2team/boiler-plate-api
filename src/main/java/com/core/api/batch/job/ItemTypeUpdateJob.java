package com.core.api.batch.job;

import com.core.api.item.entity.ItemType;
import com.core.api.item.repository.ItemCommentRepository;
import com.core.api.item.repository.ItemLikeRepository;
import com.core.api.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class ItemTypeUpdateJob {
    private final ItemRepository itemRepository;
    private final ItemLikeRepository itemLikeRepository;
    private final ItemCommentRepository itemCommentRepository;

    public void run() {
        // 아이템 전체 조회
        var items = itemRepository.findAllByTypeIn(
                Arrays.asList(ItemType.TYPE1, ItemType.TYPE2, ItemType.TYPE3, ItemType.TYPE4)
        );

        for (var item : items) {
            var itemLikeCount = itemLikeRepository.countByItemId(item.getId());
            var itemCommentCount = itemCommentRepository.countByItemId(item.getId());

            var totalCount = itemLikeCount + itemCommentCount;

            if (totalCount > 0 && totalCount <= 5 && item.getType() != ItemType.TYPE4) {
                item.upgrade(ItemType.TYPE1);
            } else if (totalCount > 5 && totalCount <= 10 && item.getType() != ItemType.TYPE4) {
                item.upgrade(ItemType.TYPE2);
            } else if (totalCount > 10 && totalCount <= 15 && item.getType() != ItemType.TYPE4) {
                item.upgrade(ItemType.TYPE3);
            } else if (totalCount > 15 && totalCount <= 20 && item.getType() != ItemType.TYPE4) {
                item.upgrade(ItemType.TYPE4);
            } else if (totalCount > 20 && totalCount <= 25) {
                item.upgrade(ItemType.TYPE5);
            }

            itemRepository.save(item);
        }
    }
}
