package com.core.api.batch.job;

import com.core.api.item.entity.Item;
import com.core.api.item.entity.ItemType;
import com.core.api.item.repository.ItemCommentRepository;
import com.core.api.item.repository.ItemLikeRepository;
import com.core.api.item.repository.ItemRepository;
import com.core.api.rank.entity.RankAddress;
import com.core.api.rank.repository.RankAddressRepository;
import com.querydsl.core.group.GroupBy;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.core.api.item.entity.QItem.item;

@Component
@RequiredArgsConstructor
public class ItemTypeUpdateJob {
    private final ItemRepository itemRepository;
    private final ItemLikeRepository itemLikeRepository;
    private final ItemCommentRepository itemCommentRepository;
    private final JPAQueryFactory queryFactory;
    private final RankAddressRepository rankAddressRepository;

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

    public void rankRun() {
        List<Item> items = queryFactory.selectFrom(item)
                .where(
                        item.address.isNotNull()
                )
                .fetch();

        Map<String, Map<ItemType, List<Item>>> groupedItems = items.stream()
                .collect(Collectors.groupingBy(Item::getAddress,
                        Collectors.groupingBy(Item::getType)));

        List<RankAddress> rankAddresses = groupedItems.entrySet().stream()
                .flatMap(entry -> entry.getValue().entrySet().stream()
                        .map(typeEntry -> {
                            String address = entry.getKey();
                            ItemType type = typeEntry.getKey();
                            List<Item> itemList = typeEntry.getValue();

                            Item firstItem = itemList.get(0);
                            BigDecimal latitude = firstItem.getLatitude();
                            BigDecimal longitude = firstItem.getLongitude();

                            int score = (itemList.stream()
                                    .mapToInt(item -> (int) calculateScore(item.getType()))
                                    .sum()) / itemList.size();

                            return RankAddress.builder()
                                    .address(address)
                                    .type(type)
                                    .latitude(latitude)
                                    .longitude(longitude)
                                    .score(score)
                                    .build();
                        }))
                .toList();

        rankAddressRepository.deleteAll();
        rankAddressRepository.saveAll(rankAddresses);
    }
    private double calculateScore(ItemType type) {
        if (type == ItemType.TYPE1)
            return 5;
        else if (type == ItemType.TYPE2)
            return 4;
        else if (type == ItemType.TYPE3)
            return 3;
        else if (type == ItemType.TYPE4)
            return 2;
        else
            return 1;
    }
}
