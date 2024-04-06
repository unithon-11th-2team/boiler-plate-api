package com.core.api.item.service;

import com.core.api.auth.AuthUser;
import com.core.api.client.AddressClient;
import com.core.api.common.util.GeoUtils;
import com.core.api.error.CommentLengthOutException;
import com.core.api.error.ErrorType;
import com.core.api.error.NotFoundException;
import com.core.api.item.dto.request.CommentSaveDto;
import com.core.api.item.dto.request.ItemSaveDto;
import com.core.api.item.dto.response.CommentSaveResponseDto;
import com.core.api.item.dto.response.ItemSaveResponseDto;
import com.core.api.item.dto.response.MyItemResponse;
import com.core.api.item.entity.Item;
import com.core.api.item.entity.ItemComment;
import com.core.api.item.repository.ItemCommentRepository;
import com.core.api.item.repository.ItemRepository;
import com.core.api.user.entity.User;
import com.core.api.user.repository.UserRepository;
import com.querydsl.jpa.JPQLQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final AddressClient addressClient;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final JPQLQueryFactory queryFactory;
    private final ItemCommentRepository itemCommentRepository;

    public ItemSaveResponseDto itemSave(AuthUser user, ItemSaveDto itemSaveDto) {
        String address = addressClient.search(itemSaveDto.getLatitude(), itemSaveDto.getLongitude())
                .getDocuments().stream().findFirst()
                .get().getRegion2depthName();

        Item item = new Item(user.getId(), itemSaveDto, address);
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

    public CommentSaveResponseDto itemComment(Long uid, CommentSaveDto commentSaveDto) {
        if (commentSaveDto.getMessage().length() > 50 || commentSaveDto.getMessage().isEmpty()) {
            throw new CommentLengthOutException(ErrorType.COMMENT_MESSAGE_LENGTH_ERROR);
        }
        ItemComment itemComment = itemCommentRepository.save(new ItemComment(uid, commentSaveDto));
        User user = userRepository.findById(uid).orElseThrow(() -> new NotFoundException(ErrorType.USER_NOT_FOUND_ERROR));
        return new CommentSaveResponseDto(user, itemComment);
    }

    public void itemCommentDelete(Long id) {
        itemCommentRepository.deleteById(id);
    }

    public List<MyItemResponse> getAllMyItems(AuthUser user) {
        return itemRepository.findAllByUid(user.getId())
                .stream()
                .map(MyItemResponse::from)
                .toList();
    }
}
