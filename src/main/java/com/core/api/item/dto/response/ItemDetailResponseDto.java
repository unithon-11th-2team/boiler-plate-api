package com.core.api.item.dto.response;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ItemDetailResponseDto {
    private Long ItemId;
    private String message;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String address;
    private List<ItemDetailCommentDto> commentList;
    private List<ItemDetailCommentLikeDto> likeList;
}
