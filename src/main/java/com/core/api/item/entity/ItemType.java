package com.core.api.item.entity;

public enum ItemType {
    TYPE1(1),
    TYPE2(2),
    TYPE3(3),
    TYPE4(4),
    TYPE5(5);

    private final int value;

    ItemType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static ItemType valueOf(int value) {
        for (ItemType itemType : values()) {
            if (itemType.getValue() == value) {
                return itemType;
            }
        }
        throw new IllegalArgumentException("Invalid ItemType value: " + value);
    }
}
