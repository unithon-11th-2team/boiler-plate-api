package com.core.api.item.entity;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class ItemTypeConverter implements AttributeConverter<ItemType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ItemType itemType) {
        return itemType.getValue();
    }

    @Override
    public ItemType convertToEntityAttribute(Integer integer) {
        return ItemType.valueOf(integer);
    }
}
