package com.core.api.item.entity;

import com.core.api.common.entity.BaseEntity;
import com.core.api.item.dto.request.ItemSaveDto;
import com.core.api.user.entity.User;
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
public class Item extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "message")
    private String message;

    @Column(name = "uid")
    private Long uid;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @Convert(converter = ItemTypeConverter.class)
    @Column(name = "type")
    private ItemType type;

    public Item(Long uid, ItemSaveDto itemSaveDto) {
        this.uid = uid;
        this.message = itemSaveDto.getMessage();
        this.latitude = new BigDecimal(String.valueOf(itemSaveDto.getLatitude()));
        this.longitude = new BigDecimal(String.valueOf(itemSaveDto.getLongitude()));
        this.type = itemSaveDto.getType();
    }

    public void upgrade(ItemType type) {
        this.type = type;
    }
}
