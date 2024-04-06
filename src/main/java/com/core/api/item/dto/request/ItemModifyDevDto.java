package com.core.api.item.dto.request;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ItemModifyDevDto {
    private BigDecimal latitude;
    private BigDecimal longitude;
    private Long itemId;
}
