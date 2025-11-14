package com.bussinburgers.models;

import com.bussinburgers.models.items.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<MenuItem> items = new ArrayList<>();

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public double getTotal() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public boolean isValid() {
        return items.size() > 0;
    }

    public String getReceiptText() {
        StringBuilder sb = new StringBuilder();

        sb.append("===== BUSSIN BURGERS RECEIPT =====\n\n");

        for (MenuItem item : items) {
            sb.append(item.getDescription()).append("\n");
        }

        sb.append("----------------------------------\n");
        sb.append("TOTAL: $").append(String.format("%.2f", getTotal())).append("\n");
        sb.append("Thank you for eating at Bussin Burgers!\n");

        return sb.toString();
    }
}
