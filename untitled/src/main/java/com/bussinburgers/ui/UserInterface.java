package com.bussinburgers.ui;

import com.bussinburgers.models.Order;
import com.bussinburgers.models.items.*;
import com.bussinburgers.models.enums.*;
import com.bussinburgers.models.enums.SideType;
import com.bussinburgers.util.ReceiptWriter;

import java.util.Scanner;

public class UserInterface {

    private Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n=̶=̶=̶=̶=̶ START =̶=̶=̶=̶=̶ ");
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
            System.out.println("\n=̶=̶=̶=̶=̶ NEW ORDER =̶=̶=̶=̶=̶");
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

        // Choose Bun Type
        BunType bun = chooseFromEnum(BunType.values(), "Choose Bun Type");

        Burger burger = new Burger(bun);

        // Add Toppings
        addRegularToppings(burger);
        addPremiumToppings(burger);

        // Special option
        System.out.print("\nAdd special option: Add bussin sauce?  (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            burger.setSpecialOption(true);
        }

        order.addItem(burger);
        System.out.println("Burger added!");
    }

    private void addRegularToppings(Burger burger) {
        RegularTopping[] toppings = RegularTopping.values();

        System.out.println("\n=̶=̶=̶=̶ Add Regular Toppings (FREE) =̶=̶=̶=̶");
        for (int i = 0; i < toppings.length; i++) {
            System.out.println((i + 1) + ") " + formatEnumName(toppings[i].name())
                    + " (" + toppings[i].getCalories() + " cal)");
        }
        System.out.println("0) Done");

        while (true) {
            System.out.println("\nSelected regular toppings: " + burger.getRegularToppings());
            System.out.print("Enter choice: ");
            int choice = readInt();

            if (choice == 0) break;

            if (choice >= 1 && choice <= toppings.length) {
                burger.addRegularTopping(toppings[choice - 1]);
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private void addPremiumToppings(Burger burger) {
        PremiumTopping[] toppings = PremiumTopping.values();

        System.out.println("\n=̶=̶=̶ Add Premium Toppings ($1.50 each) =̶=̶=̶");
        for (int i = 0; i < toppings.length; i++) {
            System.out.println((i + 1) + ") " + formatEnumName(toppings[i].name())
                    + " (" + toppings[i].getCalories() + " cal)");
        }
        System.out.println("0) Done");

        while (true) {
            System.out.println("\nSelected premium toppings: " + burger.getPremiumToppings());
            System.out.print("Enter choice: ");
            int choice = readInt();

            if (choice == 0) break;

            if (choice >= 1 && choice <= toppings.length) {
                burger.addPremiumTopping(toppings[choice - 1]);
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    // ===================== ADD DRINK =====================
    private void addDrink(Order order) {

        Drink.DrinkType type = chooseDrinkType();

        Drink.DrinkSize size = chooseFromEnum(Drink.DrinkSize.values(), "Choose Drink Size");

        Drink drink = new Drink(type, size);
        order.addItem(drink);

        System.out.println("Drink added!");
    }

    // ===================== ADD SIDE =====================
    private void addSide(Order order) {
        Side.SideType type = chooseSideType();
        if (type == null) {
            System.out.println("Side selection cancelled.");
            return;
        }

        Side side = new Side(type);   // now matches constructor
        order.addItem(side);
        System.out.println("Side added!");
        displayCurrentOrder(order);   // optional: show running order
    }


    // ===================== CHECKOUT =====================
    private void checkout(Order order) {
//        System.out.println("\n===== ORDER SUMMARY =====");
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
        System.out.println("\n=̶=̶=̶=̶=̶ " + prompt + " =̶=̶=̶=̶=̶");

        for (int i = 0; i < values.length; i++) {
            String display = formatEnumName(values[i].name());
            if (values[i] instanceof BunType rt) display += " (" + rt.getCalories() + " cal)";
            if (values[i] instanceof RegularTopping rt) display += " (" + rt.getCalories() + " cal)";
            if (values[i] instanceof PremiumTopping pt) display += " (" + pt.getCalories() + " cal)";
            if (values[i] instanceof DrinkType dt) display += " (" + dt.getCalories() + " cal)";
            if (values[i] instanceof SideType st) display += " (" + st.getCalories() + " cal)";

            System.out.println((i + 1) + ") " + display);
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

    //helper method for Drink (displays cals)
    private Drink.DrinkType chooseDrinkType() {
        System.out.println("\n--- Choose Drink Type ---");

        Drink.DrinkType[] values = Drink.DrinkType.values();

        for (int i = 0; i < values.length; i++) {
            System.out.println((i + 1) + ") "
                    + values[i].getDisplayName()
                    + " (" + values[i].getCalories() + " cal)");
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


    //helper method for Side (displays cals)
    private Side.SideType chooseSideType() {
        Side.SideType[] values = Side.SideType.values();

        System.out.println("\n=̶=̶=̶=̶=̶ Choose Side =̶=̶=̶=̶=̶");
        for (int i = 0; i < values.length; i++) {
            System.out.println((i + 1) + ") " + values[i].getDisplayName()
                    + " (" + values[i].getCalories() + " cal)");
        }
        System.out.println("0) Cancel");

        int choice;
        while (true) {
            System.out.print("Enter choice number: ");
            choice = readInt();
            if (choice == 0) return null;               // allow cancel
            if (choice >= 1 && choice <= values.length) break;
            System.out.println("Invalid choice. Try again.");
        }

        return values[choice - 1];
    }

    private void displayCurrentOrder(Order order) {
        System.out.println("\n̶=̶=̶=̶=̶ Current Order ̶=̶=̶=̶=̶");
        for (MenuItem item : order.getItems()) {
            System.out.println(item.getDescription());
        }
        System.out.println("̶=̶=̶=̶=̶̶=̶=̶=̶=̶̶=̶=̶=̶=̶̶=̶=̶=̶=̶̶=̶=̶=̶=̶");
    }
    private void showCalories(Order order) {
        System.out.println("\n̶=̶=̶=̶ CALORIE SUMMARY ̶=̶=̶=̶");
        for (MenuItem item : order.getItems()) {
            if (item instanceof Burger burger) {
                int totalCalories = 0;

                for (RegularTopping rt : burger.getRegularToppings()) {
                    totalCalories += rt.getCalories();
                }

                for (PremiumTopping pt : burger.getPremiumToppings()) {
                    totalCalories += pt.getCalories();
                }

                // Display burger summary with calories
                System.out.println("Burger - Total Calories (toppings only): " + totalCalories);
            } else if (item instanceof Drink drink) {
                System.out.println(drink.getDescription().trim()
                        + " - " + drink.getCalories() + " cal");
            } else if (item instanceof Side side) {
                System.out.println(side.getDescription().trim()
                        + " - " + side.getType().getCalories() + " cal");
            }
        }
        System.out.println("=̶=̶=̶=̶̶=̶=̶=̶=̶̶=̶=̶=̶=̶̶=̶=̶=̶=̶̶=̶=̶=̶=̶=̶=̶");
    }

    private void suggestCombo(Order order) {
        boolean hasBurger = order.getItems().stream().anyMatch(i -> i instanceof Burger);
        boolean hasDrinkOrSide = order.getItems().stream().anyMatch(i -> i instanceof Drink || i instanceof Side);

        if (hasBurger && !hasDrinkOrSide) {
            System.out.println("\n Suggestion: Add a drink or side to make it a complete combo!");
        }
    }

}