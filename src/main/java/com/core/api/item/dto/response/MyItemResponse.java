package com.core.api.item.dto.response;

import com.core.api.item.entity.Item;
import com.core.api.item.entity.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
public class MyItemResponse {
    private Long id;
    private String message;
    private Long uid;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String address;
    private ItemType type;
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public static MyItemResponse from(Item item) {
        return MyItemResponse.builder()
                .id(item.getId())
                .message(item.getMessage())
                .uid(item.getUid())
                .latitude(item.getLatitude())
                .longitude(item.getLongitude())
                .address(item.getAddress())
                .type(item.getType())
                .createdAt(item.getCreatedAt())
                .modifiedAt(item.getModifiedAt())
                .build();
    }
}
