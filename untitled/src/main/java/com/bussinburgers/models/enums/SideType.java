package com.bussinburgers.models.enums;

public enum SideType {
    FRIES("Fries"),
    CHEESE_FRIES("Cheese Fries"),
    ONION_RINGS("Onion Rings");

    private final String displayName;
    SideType(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
