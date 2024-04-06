package com.core.api.item.service;

import com.core.api.auth.AuthUser;
import com.core.api.client.AddressClient;
import com.core.api.common.util.GeoUtils;
import com.core.api.error.CommentLengthOutException;
import com.core.api.error.ErrorType;
import com.core.api.error.NotFoundException;
import com.core.api.item.dto.request.CommentSaveDto;
import com.core.api.item.dto.request.ItemSaveDto;
import com.core.api.item.dto.response.*;
import com.core.api.item.entity.*;
import com.core.api.item.repository.ItemCommentLikeRepository;
import com.core.api.item.repository.ItemCommentRepository;
import com.core.api.item.repository.ItemLikeRepository;
import com.core.api.item.repository.ItemRepository;
import com.core.api.user.entity.QUser;
import com.core.api.user.entity.User;
import com.core.api.user.repository.UserRepository;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPQLQueryFactory;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static com.core.api.item.entity.QItem.item;
import static com.core.api.item.entity.QItemComment.itemComment;
import static com.core.api.item.entity.QItemCommentLike.itemCommentLike;
import static com.core.api.item.entity.QItemLike.itemLike;
import static com.core.api.user.entity.QUser.user;

@Service
@RequiredArgsConstructor
public class ItemService {
    private final AddressClient addressClient;
    private final UserRepository userRepository;
    private final ItemRepository itemRepository;
    private final JPQLQueryFactory queryFactory;
    private final ItemCommentRepository itemCommentRepository;
    private final ItemLikeRepository itemLikeRepository;
    private final ItemCommentLikeRepository itemCommentLikeRepository;
    private final JPAQueryFactory jpaQueryFactory;

    @Transactional
    public ItemSaveResponseDto itemSave(AuthUser user, ItemSaveDto itemSaveDto) {
        var address = addressClient.search(itemSaveDto.getLatitude(), itemSaveDto.getLongitude())
                .getDocuments().stream().findFirst();

        var item = Item.builder()
                .uid(user.getId())
                .message(itemSaveDto.getMessage())
                .latitude(itemSaveDto.getLatitude())
                .longitude(itemSaveDto.getLongitude())
                .type(itemSaveDto.getType());

        if (address.isPresent()) {
            var add = address.get();
            item.address(add.getAddressName())
                    .region1depthName(add.getRegion1depthName())
                    .region2depthName(add.getRegion2depthName())
                    .region3depthName(add.getRegion3depthName());
        }

        var newItem = itemRepository.save(item.build());
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
        return itemRepository.findAllByUidOrderByCreatedAtDesc(user.getId())
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

    public ItemDetailResponseDto itemDetail(Long uId, Long itemId) {

        Item item = jpaQueryFactory.select(QItem.item)
                .from(QItem.item)
                .where(QItem.item.id.eq(itemId))
                .fetchFirst();

        List<ItemDetailCommentDto> itemCommentList = jpaQueryFactory
                .select(Projections.constructor(ItemDetailCommentDto.class,
                        itemComment.uId,
                        user.nickname,
                        itemComment.message,
                        itemCommentLike.count().as("likeCount")))
                .from(itemComment)
                .leftJoin(user).on(itemComment.uId.eq(user.id))
                .leftJoin(itemCommentLike).on(itemCommentLike.itemCommentId.eq(itemComment.id))
                .where(itemComment.itemId.eq(itemId))
                .groupBy(itemComment.id, user.id)
                .fetch();

        return new ItemDetailResponseDto(item, itemCommentList);
    }

    public Integer itemCommentLike(Long id, Long itemCommentId) {
        itemCommentLikeRepository.save(new ItemCommentLike(id, itemCommentId));

        long count = jpaQueryFactory.selectFrom(itemCommentLike)
                .where(itemCommentLike.itemCommentId.eq(itemCommentId))
                .fetchCount();
        return count > 0 ? (int) count : 0;
    }

    @Transactional
    public Integer itemCommentLikeCancel(Long id, Long itemCommentId) {
        queryFactory.delete(itemCommentLike)
                .where(itemCommentLike.uId.eq(id).and(itemCommentLike.itemCommentId.eq(itemCommentId)))
                .execute();

        long count = jpaQueryFactory.selectFrom(itemCommentLike)
                .where(itemCommentLike.itemCommentId.eq(itemCommentId))
                .fetchCount();
        return count > 0 ? (int) count : 0;
    }
}
