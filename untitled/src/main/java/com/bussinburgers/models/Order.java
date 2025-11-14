package com.bussinburgers.models;

import com.bussinburgers.models.items.MenuItem;
import com.bussinburgers.util.TimeUtil;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<MenuItem> items = new ArrayList<>();
    private LocalDateTime orderTime;

    public Order() {
        this.orderTime = LocalDateTime.now();
    }

    public void addItem(MenuItem item) {
        items.add(item);
    }

    public boolean isValid() {
        return !items.isEmpty();
    }

    public double getTotal() {
        double total = 0;
        for (MenuItem item : items) {
            total += item.getPrice();
        }
        return total;
    }

    public String getReceiptText() {
        StringBuilder sb = new StringBuilder();

        sb.append("        Bussin Burgers\n");
        sb.append("The Ultimate Burger Experience \n");
        sb.append("----------------------------------\n");

        // Order ID & date/time
        String orderId = TimeUtil.getTimestamp();
        sb.append("Order ID: ").append(orderId).append("\n");
        sb.append("Date/Time: ")
                .append(orderTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                .append("\n");
        sb.append("----------------------------------\n");

        // Items
        sb.append("Order Items:\n");
        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            sb.append((i + 1)).append(") ").append(item.getDescription());
        }

        sb.append("----------------------------------\n");

        // Total
        sb.append("Total: $").append(String.format("%.2f", getTotal())).append("\n\n");

        sb.append("Thank you for dining with us!\n");
        sb.append("Visit us at bussinburgers.com\n");

        return sb.toString();
    }

    public List<MenuItem> getItems() {
        return items;
    }
}
