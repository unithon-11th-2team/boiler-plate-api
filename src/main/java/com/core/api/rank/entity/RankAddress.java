package com.core.api.rank.entity;

import com.core.api.item.entity.enums.ItemType;
import com.core.api.item.entity.enums.ItemTypeConverter;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rank_address")
public class RankAddress {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Convert(converter = ItemTypeConverter.class)
    private ItemType type;
    private String address;
    private BigDecimal latitude;
    private BigDecimal longitude;
    private double score;
}
