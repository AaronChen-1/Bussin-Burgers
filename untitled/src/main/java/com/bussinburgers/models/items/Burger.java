package com.bussinburgers.models.items;

import com.bussinburgers.models.enums.BunType;
import com.bussinburgers.models.enums.RegularTopping;
import com.bussinburgers.models.enums.PremiumTopping;

import java.util.ArrayList;
import java.util.List;

public class Burger extends MenuItem {

    private BunType bunType;
    private boolean specialOption;
    private List<RegularTopping> regularToppings = new ArrayList<>();
    private List<PremiumTopping> premiumToppings = new ArrayList<>();

    public Burger(BunType bunType) {
        super("Burger", 4.00);
        this.bunType = bunType;
    }

    public void addRegularTopping(RegularTopping topping) {
        regularToppings.add(topping);
    }

    public void addPremiumTopping(PremiumTopping topping) {
        premiumToppings.add(topping);
    }

    public void setSpecialOption(boolean specialOption) {
        this.specialOption = specialOption;
    }

    @Override
    public double getPrice() {
        double price = basePrice;
        price += premiumToppings.size() * 1.50;
        if (specialOption) price += 1.25;
        return price;
    }

    @Override
    public String getDescription() {
        StringBuilder sb = new StringBuilder();
        sb.append("Burger (").append(formatEnumName(bunType.name())).append(" Bun)\n");

        if (!regularToppings.isEmpty()) {
            sb.append("Regular Toppings: ");
            for (RegularTopping t : regularToppings) sb.append(formatEnumName(t.name())).append(" ");
            sb.append("\n");
        }

        if (!premiumToppings.isEmpty()) {
            sb.append("Premium Toppings: ");
            for (PremiumTopping t : premiumToppings) sb.append(formatEnumName(t.name())).append(" ");
            sb.append("\n");
        }

        if (specialOption) sb.append("Special Option Included\n");

        sb.append("Price: $").append(String.format("%.2f", getPrice())).append("\n");
        return sb.toString();
    }

    public List<RegularTopping> getRegularToppings() { return regularToppings; }
    public List<PremiumTopping> getPremiumToppings() { return premiumToppings; }

    // Helper to format enums nicely
    private String formatEnumName(String name) {
        String formatted = name.replace("_", " ").toLowerCase();
        return formatted.substring(0, 1).toUpperCase() + formatted.substring(1);
    }
}