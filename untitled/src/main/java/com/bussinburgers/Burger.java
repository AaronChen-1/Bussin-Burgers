package com.bussinburgers;

import com.bussinburgers.enums.*;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class Burger extends MenuItem implements Customizable {
    private Size size;
    private BunType bun;
    private boolean animalStyle = false;

    private final Set<RegularTopping> regularToppings = new LinkedHashSet<>();
    private final Set<PremiumTopping> premiumToppings = new LinkedHashSet<>();
    private final Set<Object> extras = new LinkedHashSet<>();

    public Burger(String name, double basePrice, Size size, BunType bun) {
        super(name, basePrice);
        this.size = size;
        this.bun = bun;
    }

    public Size getSize() { return size; }
    public BunType getBun() { return bun; }
    public boolean isAnimalStyle() { return animalStyle; }
    public void setAnimalStyle(boolean animalStyle) { this.animalStyle = animalStyle; }

    @Override
    public boolean addTopping(Object topping) {
        if (topping instanceof RegularTopping) return regularToppings.add((RegularTopping)topping);
        if (topping instanceof PremiumTopping) return premiumToppings.add((PremiumTopping)topping);
        return false;
    }

    @Override
    public boolean removeTopping(Object topping) {
        if (topping instanceof RegularTopping) return regularToppings.remove((RegularTopping)topping);
        if (topping instanceof PremiumTopping) return premiumToppings.remove((PremiumTopping)topping);
        return false;
    }

    public boolean markExtra(Object topping) {
        if (topping instanceof RegularTopping && regularToppings.contains(topping)) {
            return extras.add(topping);
        }
        if (topping instanceof PremiumTopping && premiumToppings.contains(topping)) {
            return extras.add(topping);
        }
        return false;
    }

    @Override
    public double getPrice() {
        double price = basePrice;
        price += size.getExtra();
        for (RegularTopping r : regularToppings) price += 0.0;
        for (PremiumTopping p : premiumToppings) price += p.getPrice();
        for (Object o : extras) {
            if (o instanceof PremiumTopping) price += ((PremiumTopping)o).getPrice() * 0.5;
            if (o instanceof RegularTopping) price += ((RegularTopping)o).getPrice() * 0.5;
        }
        if (animalStyle) price += 1.50;
        return Math.max(0.0, price);
    }

    @Override
    public String toString() {
        String regs = regularToppings.isEmpty() ? "none" : regularToppings.stream().map(Enum::name).collect(Collectors.joining(", "));
        String prefs = premiumToppings.isEmpty() ? "none" : premiumToppings.stream().map(Enum::name).collect(Collectors.joining(", "));
        String extrasStr = extras.isEmpty() ? "none" : extras.stream().map(Object::toString).collect(Collectors.joining(", "));
        return String.format("%s [%s, %s, AnimalStyle=%s] Regular: %s | Premium: %s | Extras: %s - $%.2f",
                name, size, bun, animalStyle, regs, prefs, extrasStr, getPrice());
    }
}
