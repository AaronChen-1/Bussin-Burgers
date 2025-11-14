package com.bussinburgers.models.items;

public class Side extends MenuItem {

    public enum SideType { FRIES, CHEESE_FRIES, ONION_RINGS }

    private SideType type;

    public Side(SideType type) {
        super("Side", 2.00);
        this.type = type;
    }

    @Override
    public double getPrice() {
        double price = basePrice;
        switch (type) {
            case CHEESE_FRIES -> price += 1.00;
            case ONION_RINGS -> price += 1.50;
            default -> {}
        }
        return price;
    }

    @Override
    public String getDescription() {
        return formatEnumName(type.name()) + " - $" + String.format("%.2f", getPrice()) + "\n";
    }

    public SideType getType() { return type; }

    private String formatEnumName(String name) {
        String formatted = name.replace("_", " ").toLowerCase();
        return formatted.substring(0,1).toUpperCase() + formatted.substring(1);
    }

    @Override
    public String toString() {
        return formatEnumName(type.name());
    }
}
