package com.bussinburgers;

public class OrderItem {
    private final MenuItem item;
    private final int quantity;

    public OrderItem(MenuItem item, int quantity) {
        if (quantity <= 0) throw new IllegalArgumentException("Quantity must be > 0");
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() { return item; }
    public int getQuantity() { return quantity; }
    public double subtotal() { return item.getPrice() * quantity; }

    @Override
    public String toString() {
        return String.format("%d x %s = $%.2f", quantity, item.toString(), subtotal());
    }
}
