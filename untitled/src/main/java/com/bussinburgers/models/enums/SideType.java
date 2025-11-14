package com.bussinburgers.models.enums;

public enum SideType {

    FRIES("Fries", 320),
    CHEESE_FRIES("Cheese Fries", 450),
    ONION_RINGS("Onion Rings", 380);

    private final String displayName;
    private final int calories;

    SideType(String displayName, int calories) {
        this.displayName = displayName;
        this.calories = calories;
    }

    public String getDisplayName() {
        return displayName;
    }

    public int getCalories() {
        return calories;
    }
}