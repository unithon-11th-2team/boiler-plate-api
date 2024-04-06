package com.core.api.item.service;

import com.core.api.item.dto.response.ItemCommentDevResponse;
import com.core.api.item.dto.response.ItemDevResponse;
import com.core.api.item.dto.response.ItemLikeDevResponse;
import com.core.api.item.entity.Item;
import com.core.api.item.entity.ItemComment;
import com.core.api.item.entity.ItemLike;
import com.core.api.item.repository.ItemCommentRepository;
import com.core.api.item.repository.ItemLikeRepository;
import com.core.api.item.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ItemDevService {
    private final ItemRepository itemRepository;
    private final ItemCommentRepository itemCommentRepository;
    private final ItemLikeRepository itemLikeRepository;

    public List<ItemDevResponse> getAllItems(List<Long> ids) {
        var isEmptyOrNull = ids == null || ids.isEmpty();
        List<Item> items = isEmptyOrNull ? itemRepository.findAll() : itemRepository.findAllByIdIn(ids);

        return items
                .stream()
                .map(ItemDevResponse::from)
                .collect(Collectors.toList());
    }

    public List<ItemCommentDevResponse> getAllItemComments(List<Long> ids) {
        var isEmptyOrNull = ids == null || ids.isEmpty();
        List<ItemComment> itemComments = isEmptyOrNull ? itemCommentRepository.findAll() : itemCommentRepository.findAllByIdIn(ids);

        return itemComments
                .stream()
                .map(ItemCommentDevResponse::from)
                .collect(Collectors.toList());
    }

    public List<ItemLikeDevResponse> getAllItemLikes(List<Long> ids) {
        var isEmptyOrNull = ids == null || ids.isEmpty();
        List<ItemLike> itemLikes = isEmptyOrNull ? itemLikeRepository.findAll() : itemLikeRepository.findAllByIdIn(ids);

        return itemLikes
                .stream()
                .map(ItemLikeDevResponse::from)
                .collect(Collectors.toList());
    }
}
