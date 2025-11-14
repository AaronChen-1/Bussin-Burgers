package com.bussinburgers.models.items;

public class Side extends MenuItem {
    public Side(String name, double basePrice) {
        super(name, basePrice);
    }

    @Override
    public double getPrice() { return basePrice; }

    @Override
    public String toString() {
        return String.format("%s - $%.2f", name, getPrice());
    }
}
