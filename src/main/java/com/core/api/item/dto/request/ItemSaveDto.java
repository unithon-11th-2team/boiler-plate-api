package com.core.api.item.dto.request;

import com.core.api.item.entity.ItemType;
import lombok.Data;

@Data
public class ItemSaveDto {
    private String reason;
    private String latitude;
    private String longitude;
    private ItemType type;
}
