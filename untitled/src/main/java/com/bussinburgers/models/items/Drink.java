package com.bussinburgers.models.items;

public class Drink extends MenuItem {

    public enum DrinkSize {
        SMALL, MEDIUM, LARGE //Small drink will be defaulted maybe change later
    }

    private DrinkSize size;

    public Drink(DrinkSize size) {
        super("Drink", 1.50);
        this.size = size;
    }

    @Override
    public double getPrice() {
        double price = basePrice;

        switch (size) {
            case MEDIUM:
                price += 0.75;
                break;
            case LARGE:
                price += 1.50;
                break;
        }

        return price;
    }

    @Override
    public String getDescription() {
        return size + " Drink - $" + String.format("%.2f", getPrice()) + "\n";
    }
}
