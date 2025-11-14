package com.bussinburgers.models.enums;

public enum SideType {
    FRIES(300),
    CHEESE_FRIES(400),
    ONION_RINGS(350);

    private final int calories;

    SideType(int calories) {
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }
}
