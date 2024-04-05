package com.core.api.item.dto.request;

import com.core.api.item.entity.ItemType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemSaveDto {
    private String message;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private ItemType type;
}
