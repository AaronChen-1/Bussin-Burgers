package com.bussinburgers;

public abstract class MenuItem {
    protected String name;
    protected double basePrice;

    public MenuItem(String name, double basePrice) {
        this.name = name;
        this.basePrice = basePrice;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public abstract double getPrice();

    @Override
    public String toString() {
        return String.format("%s ($%.2f))", name, getPrice());
    }
}
