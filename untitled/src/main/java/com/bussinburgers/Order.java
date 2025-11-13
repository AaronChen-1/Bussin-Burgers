package com.bussinburgers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private static long nextId = 1;
    private final long id;
    private final LocalDateTime createdAt;
    private final List<OrderItem> items = new ArrayList<>();
    private boolean completed = false;

    public Order() {
        this.id = nextId++;
        this.createdAt = LocalDateTime.now();
    }

    public long getId() { return id; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    public void addItem(OrderItem oi) { items.add(oi); }
    public List<OrderItem> getItems() { return List.copyOf(items); }

    public double total() {
        return items.stream().mapToDouble(OrderItem::subtotal).sum();
    }

    public void complete() { this.completed = true; }
    public boolean isCompleted() { return completed; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        sb.append(String.format("Order #%d (%s)%n", id, createdAt.format(fmt)));
        sb.append("-\n".repeat(39));
        for (OrderItem oi : items) sb.append(oi).append("\n");
        sb.append("-\n".repeat(39));
        sb.append(String.format("TOTAL: $%.2f%n", total()));
        return sb.toString();
    }
}
