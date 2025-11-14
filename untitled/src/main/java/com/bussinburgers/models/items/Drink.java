package com.bussinburgers.models.items;

public class Drink extends MenuItem {

    public enum DrinkSize {
        SMALL, MEDIUM, LARGE
    }

    public enum DrinkType {
        COLA,
        LEMONADE,
        WATER,
        ICED_TEA
    }

    private DrinkSize size;
    private DrinkType type;

    public Drink(DrinkType type, DrinkSize size) {
        super("Drink", 1.50);
        this.type = type;
        this.size = size;
    }

    @Override
    public double getPrice() {
        double price = basePrice;

        switch (size) {
            case MEDIUM -> price += 0.75;
            case LARGE -> price += 1.50;
        }

        return price;
    }

    @Override
    public String getDescription() {
        return format(type) + " (" + format(size) + ") - $" +
                String.format("%.2f", getPrice()) + "\n";
    }

    private String format(Enum<?> e) {
        return e.name().toLowerCase().replace("_", " ");
    }

    public DrinkSize getSize() {
        return size;
    }

    public DrinkType getType() {
        return type;
    }
}
