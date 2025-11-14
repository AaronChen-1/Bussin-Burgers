package com.bussinburgers.models.items;

import com.bussinburgers.models.enums.DrinkType;

public class Drink extends MenuItem {

    public enum DrinkSize { SMALL, MEDIUM, LARGE }

    private DrinkType type;
    private DrinkSize size;

    public Drink(DrinkType type, DrinkSize size) {
        super("Drink", 1.50);
        this.type = type;
        this.size = size;
    }

    @Override
    public double getPrice() {
        double price = basePrice;
        switch (size) {
            case MEDIUM -> price += 0.75;
            case LARGE -> price += 1.50;
            default -> {}
        }
        return price;
    }

    @Override
    public String getDescription() {
        return formatEnumName(size.name()) + " " + formatEnumName(type.name()) + " - $" + String.format("%.2f", getPrice()) + "\n";
    }

    @Override
    public String toString() {
        return formatEnumName(size.name()) + " " + formatEnumName(type.name());
    }

    private String formatEnumName(String name) {
        String formatted = name.replace("_", " ").toLowerCase();
        return formatted.substring(0,1).toUpperCase() + formatted.substring(1);
    }
}
