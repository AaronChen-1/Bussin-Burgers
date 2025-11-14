package com.bussinburgers.models.items;

public class Side extends MenuItem {

    public enum SideType {
        FRIES,
        CHEESE_FRIES,
        ONION_RINGS
    }

    private SideType type;

    public Side(SideType type) {
        super("Side", 2.00);
        this.type = type;
    }

    @Override
    public double getPrice() {
        double price = basePrice;

        switch (type) {
            case CHEESE_FRIES:
                price += 1.00;
                break;

            case ONION_RINGS:
                price += 1.50;
                break;
        }

        return price;
    }

    @Override
    public String getDescription() {
        return type + " - $" + String.format("%.2f", getPrice()) + "\n";
    }
}
