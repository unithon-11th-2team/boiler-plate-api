package com.core.api.item.dto.response;

import com.core.api.item.entity.Item;
import com.core.api.item.entity.ItemType;
import com.core.api.item.entity.ItemTypeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
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
    private ItemType type;

    public ItemSaveResponseDto(Item newItem) {
        this.id = newItem.getId();
        this.message = newItem.getMessage();
        this.uid = newItem.getUid();
        this.latitude = newItem.getLatitude();
        this.longitude = newItem.getLongitude();
        this.type = newItem.getType();
    }
}
