package com.bussinburgers.models.items;

public class Drink extends MenuItem {
    private Size size;

    public Drink(String name, Size size) {
        super(name);
        this.size = size;
    }

    @Override
    public double getPrice() {
        return switch(size) {
            case SMALL -> 1.50;
            case MEDIUM -> 2.00;
            case LARGE -> 2.50;
        };
    }

    @Override
    public String toString() {
        return size + " " + name + " - $" + getPrice();
    }
}
