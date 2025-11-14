package com.bussinburgers.models.enums;

public enum DrinkType {
    COLA("Cola"),
    LEMONADE("Lemonade"),
    WATER("Water"),
    ICED_TEA("Iced Tea");

    private final String displayName;
    DrinkType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
