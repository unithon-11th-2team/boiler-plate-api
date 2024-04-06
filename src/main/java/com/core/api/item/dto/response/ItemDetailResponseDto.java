package com.core.api.item.dto.response;

import com.core.api.item.entity.Item;
import com.core.api.item.entity.enums.ItemType;
import com.core.api.user.entity.User;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ItemDetailResponseDto {
    private Long itemId;
    private Long uid;
    private String nickname;
    private ItemType type;
    private ItemType currentType;
    private String message;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String address;
    private List<ItemDetailCommentDto> commentList;
    private LocalDateTime createdAt;
    private Long likeCounts;

    public ItemDetailResponseDto(
            Item item,
            List<ItemDetailCommentDto> itemCommentList,
            Long likeCounts,
            User use1) {
        this.itemId = item.getId();
        this.uid = item.getUid();
        this.type = item.getType();
        this.currentType = item.getCurrentType();
        this.message = item.getMessage();
        this.latitude = item.getLatitude();
        this.longitude = item.getLongitude();
        this.address = item.getAddress();
        this.commentList = itemCommentList;
        this.createdAt = item.getCreatedAt();
        this.likeCounts = likeCounts;
        this.nickname = use1.getNickname();
    }
}
