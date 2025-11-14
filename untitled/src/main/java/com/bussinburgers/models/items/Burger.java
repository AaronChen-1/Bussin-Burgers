package com.bussinburgers.models.items;

import java.util.ArrayList;

public class Burger extends MenuItem {
    private double basePrice;
    private ArrayList<String> standardToppings = new ArrayList<>();
    private ArrayList<String> premiumToppings = new ArrayList<>();
    private ArrayList<String> extraToppings = new ArrayList<>();

    public Burger(String name, double basePrice) {
        super(name);
        this.basePrice = basePrice;
    }

    public void addTopping(ToppingType type, String toppingName) {
        switch(type) {
            case STANDARD -> standardToppings.add(toppingName);
            case PREMIUM -> premiumToppings.add(toppingName);
            case EXTRA -> extraToppings.add(toppingName);
        }
    }

    @Override
    public double getPrice() {
        return basePrice + (premiumToppings.size() * 1.50) + (extraToppings.size() * 0.75);
    }

    @Override
    public String toString() {
        return name + " - Base: $" + basePrice +
                "\nStandard: " + standardToppings +
                "\nPremium: " + premiumToppings +
                "\nExtra: " + extraToppings +
                "\nTotal: $" + getPrice();
    }
}
