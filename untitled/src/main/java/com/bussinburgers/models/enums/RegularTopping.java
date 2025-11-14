package com.bussinburgers.models.enums;

public enum RegularTopping {
    LETTUCE(5),
    TOMATO(10),
    PICKLES(5),
    ONIONS(10),
    GRILLED_ONIONS(15),
    JALAPENOS(5);

    private final int calories;

    RegularTopping(int calories) {
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }
}