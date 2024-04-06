package com.core.api.item.dto.response;

import com.core.api.item.entity.Item;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class ItemDetailResponseDto {
    private Long ItemId;
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
            Long likeCounts
    ) {
        this.ItemId = item.getId();
        this.message = item.getMessage();
        this.latitude = item.getLatitude();
        this.longitude = item.getLongitude();
        this.address = item.getAddress();
        this.commentList = itemCommentList;
        this.createdAt = item.getCreatedAt();
        this.likeCounts = likeCounts;
    }
}
