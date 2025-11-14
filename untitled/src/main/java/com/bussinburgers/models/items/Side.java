package com.bussinburgers.models.items;

import java.util.Locale;

public class Side extends MenuItem {

    // Inner enum - THE only SideType you should use in the project
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

        @Override
        public String toString() {
            return displayName;
        }
    }

    private SideType type;

    public Side(SideType type) {
        super(type.getDisplayName(), 2.00);
        this.type = type;
    }

    @Override
    public double getPrice() {
        double price = basePrice;
        switch (type) {
            case CHEESE_FRIES -> price += 1.00;
            case ONION_RINGS -> price += 1.50;
            default -> {}
        }
        return price;
    }

    @Override
    public String getDescription() {
        return type.getDisplayName() + " - " + "$" + String.format("%.2f", getPrice()) + "\n";
    }

    public SideType getType() {
        return type;
    }
}