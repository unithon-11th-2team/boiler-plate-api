package com.core.api.item.service;

import com.core.api.auth.AuthUser;
import com.core.api.common.util.GeoUtils;
import com.core.api.item.dto.request.ItemSaveDto;
import com.core.api.item.dto.response.ItemSaveResponseDto;
import com.core.api.item.entity.Item;
import com.core.api.item.repository.ItemRepository;
import com.core.api.user.repository.UserRepository;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.core.api.item.entity.QItem.item;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final JPQLQueryFactory queryFactory;

    public ItemSaveResponseDto itemSave(AuthUser user, ItemSaveDto itemSaveDto) {
        Item item = new Item(user.getId(), itemSaveDto);
        var newItem = itemRepository.save(item);
        return new ItemSaveResponseDto(newItem);
    }

    public List<ItemSaveResponseDto> itemList(BigDecimal latitude, BigDecimal longitude) {
        List<Item> allItems = itemRepository.findAll();
        double lat = latitude.doubleValue();
        double lon = longitude.doubleValue();

        return allItems.stream()
                .filter(item -> GeoUtils.calculateDistance(lat, lon, item.getLatitude().doubleValue(), item.getLongitude().doubleValue()) <= 100)
                .map(ItemSaveResponseDto::new)
                .toList();
    }
}
