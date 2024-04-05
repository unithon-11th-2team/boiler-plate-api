package com.core.api.item.service;

import com.core.api.auth.AuthUser;
import com.core.api.item.dto.request.ItemSaveDto;
import com.core.api.item.dto.response.ItemSaveResponseDto;
import com.core.api.item.entity.Item;
import com.core.api.item.repository.ItemRepository;
import com.core.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;

    public ItemSaveResponseDto itemSave(AuthUser user, ItemSaveDto itemSaveDto) {
        Item item = new Item(user.getId(), itemSaveDto);
        var newItem = itemRepository.save(item);
        return new ItemSaveResponseDto(newItem);
    }
}
