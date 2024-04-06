package com.core.api.rank.service;

import com.core.api.item.entity.enums.ItemType;
import com.core.api.rank.dto.RankDto;
import com.core.api.rank.entity.RankAddress;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

import static com.core.api.rank.entity.QRankAddress.rankAddress;

@Service
@RequiredArgsConstructor
public class RankService {
    private final JPAQueryFactory queryFactory;

    public List<RankDto> getRanks(String type) {
        List<RankAddress> rankAddresses = queryFactory.selectFrom(rankAddress)
                .where(typeConverters(type))
                .orderBy(rankAddress.type.asc())
                .limit(10)
                .fetch();

        return IntStream.range(0, rankAddresses.size())
                .mapToObj(i -> {
                    RankAddress rankAddress = rankAddresses.get(i);
                    return RankDto.builder()
                            .rank(i + 1) // 1부터 시작하는 인덱스
                            .type(rankAddress.getType().name())
                            .address(rankAddress.getAddress())
                            .latitude(rankAddress.getLatitude())
                            .longitude(rankAddress.getLongitude())
                            .score(rankAddress.getScore())
                            .build();
                })
                .toList();
    }

    private BooleanExpression typeConverters(String type) {
        if (!type.equals("paradise")) {
            return rankAddress.type.eq(ItemType.TYPE1).or(rankAddress.type.eq(ItemType.TYPE2)).or(rankAddress.type.eq(ItemType.TYPE3));
        } else {
            return rankAddress.type.eq(ItemType.TYPE4).or(rankAddress.type.eq(ItemType.TYPE5));
        }
    }
}
