package com.core.api.item.entity;

import com.core.api.common.entity.BaseEntity;
import com.core.api.item.dto.request.ItemSaveDto;
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
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "item")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Item extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message", columnDefinition = "varchar(512)")
    private String message;

    @Column(name = "uid")
    private Long uid;

    @Column(name = "latitude", columnDefinition = "decimal(38,10)")
    private BigDecimal latitude;

    @Column(name = "longitude", columnDefinition = "decimal(38,10)")
    private BigDecimal longitude;

    @Column(name = "address", columnDefinition = "varchar(512)")
    private String address;

    @Column(name = "region_1depth_name", columnDefinition = "varchar(512)")
    private String region1depthName;

    @Column(name = "region_2depth_name", columnDefinition = "varchar(512)")
    private String region2depthName;

    @Column(name = "region_3depth_name", columnDefinition = "varchar(512)")
    private String region3depthName;

    @Convert(converter = ItemTypeConverter.class)
    @Column(name = "type")
    private ItemType type;

    @Convert(converter = ItemTypeConverter.class)
    @Column(name = "current_type")
    private ItemType currentType;

    public Item(Long uid, ItemSaveDto itemSaveDto, String address) {
        this.uid = uid;
        this.message = itemSaveDto.getMessage();
        this.latitude = itemSaveDto.getLatitude();
        this.longitude = itemSaveDto.getLongitude();
        this.type = itemSaveDto.getType();
        this.address = address;
    }

    public void upgrade(ItemType type) {
        this.currentType = type;
    }
}
