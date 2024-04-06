package com.core.api.rank.dto;

import com.core.api.item.entity.ItemType;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class RankDto {
    private int rank;
    private String type;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private double score;
}
