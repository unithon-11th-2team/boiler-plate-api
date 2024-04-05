package com.core.api.item.entity;

/** 나락 -> 극락 */
public enum ItemType {
    TYPE1(1),
    // 0 ~ 5
    TYPE2(2),
    // 6 ~ 10
    TYPE3(3),
    // 11 ~ 15
    TYPE4(4),
    // 16 ~ 20
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
