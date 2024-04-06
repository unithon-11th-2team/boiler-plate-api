package com.core.api.item.service;

import com.core.api.item.dto.response.ItemDevResponse;
import com.core.api.item.entity.Item;
import com.core.api.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemDevService {
    private final ItemRepository itemRepository;

    public List<ItemDevResponse> getAll(List<Long> ids) {
        var isEmptyOrNull = ids == null || ids.isEmpty();
        List<Item> items = isEmptyOrNull ? itemRepository.findAll() : itemRepository.findAllByIdIn(ids);

        return items
                .stream()
                .map(ItemDevResponse::from)
                .collect(Collectors.toList());
    }
}
