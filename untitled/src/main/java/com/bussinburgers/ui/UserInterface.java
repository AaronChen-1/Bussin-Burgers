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
                case 4 -> checkout(order);
                case 5 -> {
                    System.out.println("Order cancelled.");
                    return;
                }
                default -> System.out.println("Invalid choice.");
            }
        }
    }

    // ============================================================
    //                      ADD BURGER
    // ============================================================

    private void addBurger(Order order) {
        System.out.println("\n--- Choose Size ---");
        for (BurgerSize size : BurgerSize.values()) {
            System.out.println("- " + size);
        }
        System.out.print("Enter size: ");
        BurgerSize size = BurgerSize.valueOf(scanner.next().toUpperCase());

        System.out.println("\n--- Choose Bun Type ---");
        for (BunType bun : BunType.values()) {
            System.out.println("- " + bun);
        }
        System.out.print("Enter bun: ");
        BunType bun = BunType.valueOf(scanner.next().toUpperCase());

        Burger burger = new Burger(size, bun);

        addRegularToppings(burger);
        addPremiumToppings(burger);

        System.out.print("\nAdd special option (toasted/animal)? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            burger.setSpecialOption(true);
        }

        order.addItem(burger);
        System.out.println("Burger added!");
    }

    private void addRegularToppings(Burger burger) {
        System.out.println("\n--- Add Regular Toppings (FREE) ---");
        System.out.println("Type 'done' to finish.");

        for (RegularTopping t : RegularTopping.values()) {
            System.out.println("- " + t);
        }

        while (true) {
            System.out.print("Add topping: ");
            String input = scanner.next();

            if (input.equalsIgnoreCase("done"))
                break;

            try {
                RegularTopping topping = RegularTopping.valueOf(input.toUpperCase());
                burger.addRegularTopping(topping);
            } catch (Exception e) {
                System.out.println("Invalid topping.");
            }
        }
    }

    private void addPremiumToppings(Burger burger) {
        System.out.println("\n--- Add Premium Toppings ($1.50 each) ---");
        System.out.println("Type 'done' to finish.");

        for (PremiumTopping t : PremiumTopping.values()) {
            System.out.println("- " + t);
        }

        while (true) {
            System.out.print("Add topping: ");
            String input = scanner.next();

            if (input.equalsIgnoreCase("done"))
                break;

            try {
                PremiumTopping topping = PremiumTopping.valueOf(input.toUpperCase());
                burger.addPremiumTopping(topping);
            } catch (Exception e) {
                System.out.println("Invalid premium topping.");
            }
        }
    }

    // ============================================================
    //                     ADD DRINK
    // ============================================================

    private void addDrink(Order order) {
        System.out.println("\n--- Choose Drink Size ---");
        for (Drink.DrinkSize size : Drink.DrinkSize.values()) {
            System.out.println("- " + size);
        }

        System.out.print("Enter size: ");
        Drink.DrinkSize size = Drink.DrinkSize.valueOf(scanner.next().toUpperCase());

        Drink drink = new Drink(size);
        order.addItem(drink);

        System.out.println("Drink added!");
    }

    // ============================================================
    //                     ADD SIDE
    // ============================================================

    private void addSide(Order order) {
        System.out.println("\n--- Choose Side ---");
        for (Side.SideType type : Side.SideType.values()) {
            System.out.println("- " + type);
        }

        System.out.print("Enter side: ");
        Side.SideType type = Side.SideType.valueOf(scanner.next().toUpperCase());

        Side side = new Side(type);
        order.addItem(side);

        System.out.println("Side added!");
    }

    // ============================================================
    //                     CHECKOUT
    // ============================================================

    private void checkout(Order order) {
        if (!order.isValid()) {
            System.out.println("\nOrder must contain at least 1 item.");
            return;
        }

        System.out.println("\n===== ORDER SUMMARY =====");
        System.out.println(order.getReceiptText());

        System.out.print("Complete order? (y/n): ");
        if (scanner.next().equalsIgnoreCase("y")) {
            ReceiptWriter.writeReceipt(order.getReceiptText());
            System.out.println("Order completed!");
        }
    }

    // ============================================================
    //                     SAFE INPUT
    // ============================================================

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println("Enter a valid number.");
            scanner.next();
        }
        return scanner.nextInt();
    }
}
