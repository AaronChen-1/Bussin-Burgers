package com.bussinburgers.enums;

public enum RegularTopping {
    LETTUCE(0.00),
    TOMATO(0.00),
    PICKLES(0.00),
    ONION(0.00);

    private final double price;
    RegularTopping(double price) { this.price = price; }
    public double getPrice() { return price; }
}
