package com.bussinburgers.models.enums;

public enum DrinkType {
    COLA(150),
    LEMONADE(120),
    WATER(0),
    ICED_TEA(80);

    private final int calories;

    DrinkType(int calories) {
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }
}
