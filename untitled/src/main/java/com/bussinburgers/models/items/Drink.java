package com.bussinburgers.models.items;

import com.bussinburgers.models.items.MenuItem;

public class Drink extends MenuItem {

    // --------------------------
    // ENUMS
    // --------------------------

    public enum DrinkType {
        COLA("Coca Cola", 140),
        DIET_COLA("Diet Cola", 0),
        LEMONADE("Lemonade", 120),
        ORANGE_SODA("Orange Soda", 150),
        WATER("Water", 0),
        ICED_TEA("Iced Tea", 80);

        private final String displayName;
        private final int calories;

        DrinkType(String displayName, int calories) {
            this.displayName = displayName;
            this.calories = calories;
        }

        public String getDisplayName() { return displayName; }
        public int getCalories() { return calories; }
    }

    public enum DrinkSize {
        SMALL("Small"),
        MEDIUM("Medium"),
        LARGE("Large");

        private final String displayName;

        DrinkSize(String displayName) {
            this.displayName = displayName;
        }

        public String getDisplayName() { return displayName; }
    }

    // --------------------------
    // CLASS FIELDS
    // --------------------------

    private DrinkType type;
    private DrinkSize size;

    // --------------------------
    // CONSTRUCTOR
    // --------------------------

    public Drink(DrinkType type, DrinkSize size) {
        super(type.getDisplayName(), 1.50); // base price (will adjust soon)
        this.type = type;
        this.size = size;
    }

    // --------------------------
    // PRICE CALCULATION
    // --------------------------

    @Override
    public double getPrice() {
        double price = 1.50;

        switch (size) {
            case MEDIUM -> price += 0.50;
            case LARGE -> price += 1.00;
        }

        return price;
    }

    // --------------------------
    // DESCRIPTION FOR RECEIPT
    // --------------------------

    @Override
    public String getDescription() {
        return size.getDisplayName() + " " + type.getDisplayName()
                + " - $" + String.format("%.2f", getPrice()) ;
    }

    // --------------------------
    // GETTERS
    // --------------------------

    public DrinkType getType() { return type; }
    public DrinkSize getSize() { return size; }
    public int getCalories() { return type.getCalories(); }
}
