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
import com.core.api.item.entity.ItemLike;
import com.core.api.item.repository.ItemCommentRepository;
import com.core.api.item.repository.ItemLikeRepository;
import com.core.api.item.repository.ItemRepository;
import com.core.api.user.entity.User;
import com.core.api.user.repository.UserRepository;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.core.api.item.entity.QItemComment.itemComment;
import static com.core.api.item.entity.QItemLike.itemLike;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final AddressClient addressClient;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final JPQLQueryFactory queryFactory;
    private final ItemCommentRepository itemCommentRepository;
    private final ItemLikeRepository itemLikeRepository;
    private final JPAQueryFactory jpaQueryFactory;


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

    @Transactional
    public void itemCommentDelete(Long id, Long userId) {
        jpaQueryFactory.delete(itemComment)
                .where(itemComment.itemId.eq(id).and(itemComment.uId.eq(userId)))
                .execute();
    }

    public void itemLike(Long uId, Long itemId) {
        itemLikeRepository.save(new ItemLike(uId, itemId));
    }

    @Transactional
    public void itemLikeCancel(Long uId, Long itemId) {
        queryFactory.delete(itemLike)
                .where(itemLike.uId.eq(uId).and(itemLike.itemId.eq(itemId)))
                .execute();
    }

    public List<MyItemResponse> getAllMyItems(AuthUser user) {
        return itemRepository.findAllByUid(user.getId())
                .stream()
                .map(MyItemResponse::from)
                .toList();
    }

    public Item findItemByIdAndUid(Long id, Long uid) {
        return itemRepository.findByIdAndUid(id, uid)
                .orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND_ITEM_ERROR));
    }

    public void deleteById(Long id) {
        itemRepository.deleteById(id);
    }
}
