package com.bussinburgers.models.enums;

public enum BunType {
    REGULAR("Regular"),
    WHOLE_WHEAT("Whole Wheat"),
    LETTUCE_WRAP("Lettuce Wrap");

    private final String displayName;
    BunType(String displayName) {
        this.displayName = displayName;
    }
    @Override
    public String toString() {
        return displayName;
    }
}

