package com.bussinburgers.models.enums;

public enum BunType {
    REGULAR(150),
    WHOLE_WHEAT(140),
    LETTUCE_WRAP(50);

    private final int calories;

    BunType(int calories) {
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }
}
