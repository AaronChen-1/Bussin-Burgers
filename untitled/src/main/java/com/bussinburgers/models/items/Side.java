package com.bussinburgers.models.items;

public class Side extends MenuItem {
    public Side(String name) {
        super(name);
    }

    @Override
    public double getPrice() {
        if(name.equalsIgnoreCase("Fries")) return 2.00;
        if(name.equalsIgnoreCase("Onion Rings")) return 2.50;
        return 1.50;
    }
}