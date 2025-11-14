package com.bussinburgers.models.items;

import com.bussinburgers.models.enums.BunType;
import com.bussinburgers.models.enums.RegularTopping;
import com.bussinburgers.models.enums.PremiumTopping;

import java.util.ArrayList;
import java.util.List;

public class Burger extends MenuItem {

    private BunType bunType;
    private boolean specialOption; // e.g. "Toasted" or "Animal Style"
    private List<RegularTopping> regularToppings = new ArrayList<>();
    private List<PremiumTopping> premiumToppings = new ArrayList<>();

    public Burger(BunType bunType) {
        super("Burger", 4.00); // base price of a small burger
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
        sb.append("Burger (").append(bunType).append(" Bun)\n");

        if (!regularToppings.isEmpty()) {
            sb.append("Regular Toppings: ");
            for (RegularTopping t : regularToppings) sb.append(t).append(" ");
            sb.append("\n");
        }

        if (!premiumToppings.isEmpty()) {
            sb.append("Premium Toppings: ");
            for (PremiumTopping t : premiumToppings) sb.append(t).append(" ");
            sb.append("\n");
        }

        if (specialOption) sb.append("Special Option Included\n");

        sb.append("Price: $").append(String.format("%.2f", getPrice())).append("\n");
        return sb.toString();
    }

    public BunType getBunType() { return bunType; }
    public boolean hasSpecialOption() { return specialOption; }
}