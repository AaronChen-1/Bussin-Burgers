package com.bussinburgers.models.items;

public abstract class MenuItem {
    protected String name;

    public MenuItem(String name) {
        this.name = name;
    }

    public abstract double getPrice();

    @Override
    public String toString() {
        return name + " - $" + getPrice();
    }
}
