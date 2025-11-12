package com.bussinburgers.enums;

public enum PremiumTopping {
    CHEESE(0.75),
    BACON(1.25),
    EXTRA_PATTY(2.50),
    AVOCADO(1.00);

    private final double price;
    PremiumTopping(double price) { this.price = price; }
    public double getPrice() { return price; }
}
