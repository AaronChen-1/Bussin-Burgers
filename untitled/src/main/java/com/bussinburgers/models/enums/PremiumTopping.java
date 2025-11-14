package com.bussinburgers.models.enums;

public enum PremiumTopping {
    CHEESE(100),
    BACON(150),
    AVOCADO(80),
    EXTRA_PATTY(250);

    private final int calories;

    PremiumTopping(int calories) {
        this.calories = calories;
    }

    public int getCalories() {
        return calories;
    }
}
