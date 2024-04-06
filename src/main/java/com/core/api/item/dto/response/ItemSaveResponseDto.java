package com.core.api.item.dto.response;

import com.core.api.item.entity.Item;
import com.core.api.item.entity.enums.ItemType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ItemSaveResponseDto {
    private Long id;
    private String message;
    private Long uid;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private String address;
    private ItemType type;
    private Boolean isMine = false;

    public ItemSaveResponseDto(Item newItem) {
        this.id = newItem.getId();
        this.message = newItem.getMessage();
        this.uid = newItem.getUid();
        this.latitude = newItem.getLatitude();
        this.longitude = newItem.getLongitude();
        this.address = newItem.getAddress();
        this.type = newItem.getType();
    }
}
