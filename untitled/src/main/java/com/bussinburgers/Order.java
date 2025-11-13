package com.bussinburgers;

import java.util.ArrayList;

public class Order {
    private ArrayList<MenuItem> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public double calculateTotal() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public boolean isValid() {
        return !items.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (MenuItem item : items) {
            sb.append(item).append("\n");
        }
        sb.append("Total: $").append(String.format("%.2f", calculateTotal()));
        return sb.toString();
    }
}
