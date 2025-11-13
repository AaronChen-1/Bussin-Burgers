package com.bussinburgers;

import com.bussinburgers.enums.*;

public class Drink extends MenuItem {
    private DrinkType drinkType;
    private Size size;

    public Drink(DrinkType drinkType, Size size) {
        super(drinkType.name() + " Drink", 1.00);
        this.drinkType = drinkType;
        this.size = size;
    }

    @Override
    public double getPrice() {
        return basePrice + size.getExtra() * 0.5; // smaller size extras for drinks
    }

    @Override
    public String toString() {
        return String.format("%s [%s] - $%.2f", drinkType.name(), size, getPrice());
    }
}
