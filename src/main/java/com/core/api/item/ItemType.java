package com.core.api.item;

public enum ItemType {
    TYPE1(1),
    TYPE2(2),
    TYPE3(3),
    TYPE4(4),
    TYPE5(5),
    TYPE6(6),
    TYPE7(7),
    TYPE8(8),
    TYPE9(9),
    TYPE10(10);

    private final int value;

    ItemType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
