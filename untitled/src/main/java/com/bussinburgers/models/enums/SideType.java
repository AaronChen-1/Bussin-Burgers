package com.bussinburgers.models.enums;

public enum SideType {
    FRIES("Fries", 320),
    ONION_RINGS("Onion Rings", 410),
    SALAD("Side Salad", 150),
    MOZZARELLA_STICKS("Mozzarella Sticks", 480);

    private final String displayName;
    private final int calories;

    SideType(String displayName, int calories) {
        this.displayName = displayName;
        this.calories = calories;
    }

    public String getDisplayName() { return displayName; }
    public int getCalories() { return calories; }
}

