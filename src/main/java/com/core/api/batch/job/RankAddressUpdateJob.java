package com.core.api.batch.job;

import com.core.api.item.entity.Item;
import com.core.api.item.entity.enums.ItemType;
import com.core.api.rank.entity.RankAddress;
import com.core.api.rank.repository.RankAddressRepository;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.core.api.item.entity.QItem.item;

@Component
@RequiredArgsConstructor
public class RankAddressUpdateJob {
    private final JPAQueryFactory queryFactory;
    private final RankAddressRepository rankAddressRepository;

    public void rankRun() {
        List<Item> items = queryFactory.selectFrom(item)
                .where(item.address.isNotNull())
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
