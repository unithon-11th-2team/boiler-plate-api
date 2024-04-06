package com.core.api.rank.entity;

import com.core.api.item.entity.enums.ItemType;
import com.core.api.item.entity.enums.ItemTypeConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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

    @Column(name = "address", columnDefinition = "varchar(512)")
    private String address;

    @Column(name = "latitude", columnDefinition = "decimal(38,10)")
    private BigDecimal latitude;

    @Column(name = "longitude", columnDefinition = "decimal(38,10)")
    private BigDecimal longitude;

    @Column(name = "score")
    private double score;
}
