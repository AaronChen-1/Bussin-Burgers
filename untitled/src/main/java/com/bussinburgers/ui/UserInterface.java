package com.bussinburgers.ui;

import com.bussinburgers.models.Order;
import com.bussinburgers.models.enums.*;
import com.bussinburgers.models.items.*;
import com.bussinburgers.util.ReceiptWriter;

import java.util.Scanner;

public class UserInterface {

    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n===== BUSSIN BURGERS POS =====");
            System.out.println("1) New Order");
            System.out.println("2) Exit");
            System.out.print("Choose: ");

            int choice = readInt();

            switch (choice) {
                case 1 -> startOrder();
                case 2 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    private void startOrder() {
        Order order = new Order();

        while (true) {
            System.out.println("\n===== NEW ORDER =====");
            System.out.println("1) Add Burger");
            System.out.println("2) Add Drink");
            System.out.println("3) Add Side");
            System.out.println("4) Checkout");
            System.out.println("5) Cancel Order");
            System.out.print("Choose: ");

            int choice = readInt();

            switch (choice) {
                case 1 -> addBurger(order);
                case 2 -> addDrink(order);
                case 3 -> addSide(order);
                case 4 -> {
                    if (!order.isValid()) {
                        System.out.println("\nOrder must contain at least 1 item (burger, drink, or side).");
                        break;
                    }
                    checkout(order);
                    return;
                }
                case 5 -> {
                    System.out.println("Order cancelled.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // ===================== ADD BURGER =====================
    private void addBurger(Order order) {
        BunType bun = chooseFromEnum(BunType.values(), "Choose Bun Type");
        Burger burger = new Burger(bun);

        addRegularToppings(burger);
        addPremiumToppings(burger);

        System.out.print("\nAdd special option (toasted/animal)? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            burger.setSpecialOption(true);
        }

        order.addItem(burger);
        System.out.println("Burger added!");
        displayCurrentOrder(order); // Show current order after burger added
    }

    private void addRegularToppings(Burger burger) {
        RegularTopping[] toppings = RegularTopping.values();
        System.out.println("\n--- Add Regular Toppings (FREE) ---");
        for (int i = 0; i < toppings.length; i++) {
            System.out.println((i + 1) + ") " + formatEnumName(toppings[i].name()));
        }
        System.out.println("0) Done");

        while (true) {
            if (!burger.getRegularToppings().isEmpty()) {
                System.out.print("Selected: ");
                for (RegularTopping t : burger.getRegularToppings()) {
                    System.out.print(formatEnumName(t.name()) + " ");
                }
                System.out.println();
            }

            System.out.print("Enter choice: ");
            int choice = readInt();

            if (choice == 0) break;

            if (choice >= 1 && choice <= toppings.length) {
                RegularTopping selected = toppings[choice - 1];
                if (!burger.getRegularToppings().contains(selected)) {
                    burger.addRegularTopping(selected);
                } else {
                    System.out.println(formatEnumName(selected.name()) + " already added.");
                }
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void addPremiumToppings(Burger burger) {
        PremiumTopping[] toppings = PremiumTopping.values();
        System.out.println("\n--- Add Premium Toppings ($1.50 each) ---");
        for (int i = 0; i < toppings.length; i++) {
            System.out.println((i + 1) + ") " + formatEnumName(toppings[i].name()));
        }
        System.out.println("0) Done");

        while (true) {
            if (!burger.getPremiumToppings().isEmpty()) {
                System.out.print("Selected: ");
                for (PremiumTopping t : burger.getPremiumToppings()) {
                    System.out.print(formatEnumName(t.name()) + " ");
                }
                System.out.println();
            }

            System.out.print("Enter choice: ");
            int choice = readInt();

            if (choice == 0) break;

            if (choice >= 1 && choice <= toppings.length) {
                PremiumTopping selected = toppings[choice - 1];
                if (!burger.getPremiumToppings().contains(selected)) {
                    burger.addPremiumTopping(selected);
                } else {
                    System.out.println(formatEnumName(selected.name()) + " already added.");
                }
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    // ===================== ADD DRINK =====================
    private void addDrink(Order order) {
        DrinkType type = chooseFromEnum(DrinkType.values(), "Choose Drink Type");
        Drink.DrinkSize size = chooseFromEnum(Drink.DrinkSize.values(), "Choose Drink Size");

        Drink drink = new Drink(type, size);
        order.addItem(drink);
        System.out.println("Drink added!");
        displayCurrentOrder(order);
    }

    // ===================== ADD SIDE =====================
    private void addSide(Order order) {
        Side.SideType type = chooseFromEnum(Side.SideType.values(), "Choose Side");
        Side side = new Side(type);
        order.addItem(side);
        System.out.println("Side added!");
        displayCurrentOrder(order);
    }

    // ===================== CHECKOUT =====================
    private void checkout(Order order) {
        System.out.println("\n===== ORDER SUMMARY =====");
        System.out.println(order.getReceiptText());

        System.out.print("Complete order? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            ReceiptWriter.writeReceipt(order.getReceiptText());
            System.out.println("Order completed!");
        } else {
            System.out.println("Order not completed.");
        }
    }

    // ===================== HELPER METHODS =====================
    private <T extends Enum<T>> T chooseFromEnum(T[] values, String prompt) {
        System.out.println("\n--- " + prompt + " ---");
        for (int i = 0; i < values.length; i++) {
            System.out.println((i + 1) + ") " + formatEnumName(values[i].name()));
        }

        int choice;
        while (true) {
            System.out.print("Enter choice number: ");
            choice = readInt();
            if (choice >= 1 && choice <= values.length) break;
            System.out.println("Invalid choice. Try again.");
        }
        return values[choice - 1];
    }

    // Formats enum names like "GRILLED_ONIONS" -> "Grilled Onions"
    private String formatEnumName(String name) {
        String formatted = name.replace("_", " ").toLowerCase();
        return formatted.substring(0, 1).toUpperCase() + formatted.substring(1);
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Enter a valid number.");
            scanner.next();
        }
        return scanner.nextInt();
    }

    // ===================== DISPLAY CURRENT ORDER =====================
    private void displayCurrentOrder(Order order) {
        if (order.getItems().isEmpty()) return;

        System.out.println("\n--- Current Order ---");
        int count = 1;
        double total = 0;
        for (var item : order.getItems()) {
            System.out.println(count + ") " + item.getDescription());
            total += item.getPrice();
            count++;
        }
        System.out.println("--------------------");
        System.out.println("Current total: $" + String.format("%.2f", total));
        System.out.println("--------------------\n");
    }
}
