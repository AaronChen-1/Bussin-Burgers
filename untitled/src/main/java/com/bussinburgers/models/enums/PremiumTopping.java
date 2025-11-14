package com.bussinburgers.models.enums;

public enum PremiumTopping {
    CHEESE("Cheese"),
    BACON("Bacon"),
    AVOCADO("Avocado"),
    EXTRA_PATTY("Extra Patty");

    private final String displayName;
    PremiumTopping(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName; }
}

