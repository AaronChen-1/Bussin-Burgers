package com.bussinburgers;

import com.bussinburgers.enums.*;
import java.util.Scanner;

public class UserInterface {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Welcome to Bussin Burgers!");
        boolean running = true;
        while (running) {
            System.out.println("\nMain Menu: 1) New Order  2) Exit");
            System.out.print("> ");
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> handleOrder();
                case "2" -> { running = false; System.out.println("Come again!"); }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void handleOrder() {
        Order order = new Order();
        boolean ordering = true;
        while (ordering) {
            System.out.println("\nOrder Menu: 1) Add Burger  2) Add Drink  3) Add Side  4) Checkout  5) Cancel Order");
            System.out.print("> ");
            String opt = scanner.nextLine().trim();
            switch (opt) {
                case "1" -> addBurgerFlow(order);
                case "2" -> addDrinkFlow(order);
                case "3" -> addSideFlow(order);
                case "4" -> {
                    if (order.getItems().isEmpty()) {
                        System.out.println("You must order at least one drink or side if no items were added.");
                        break;
                    }
                    order.complete();
                    System.out.println("\n--- Order Summary ---");
                    System.out.println(order);
                    ReceiptWriter.writeReceipt(order);
                    ordering = false;
                }
                case "5" -> { System.out.println("Order canceled."); ordering = false; }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void addBurgerFlow(Order order) {
        System.out.print("Burger name (e.g., Classic): ");
        String name = scanner.nextLine().trim();
        System.out.print("Base price (e.g., 4.99): ");
        double base = readDouble(4.99);

        System.out.println("Choose size: 1) SINGLE 2) DOUBLE 3) TRIPLE");
        String s = scanner.nextLine().trim();
        Size size = switch (s) {
            case "2" -> Size.DOUBLE;
            case "3" -> Size.TRIPLE;
            default -> Size.SINGLE;
        };

        System.out.println("Choose bun: 1) WHITE 2) WHEAT");
        String b = scanner.nextLine().trim();
        BunType bun = b.equals("2") ? BunType.WHEAT : BunType.WHITE;

        Burger burger = new Burger(name, base, size, bun);

        // Regular toppings
        boolean adding = true;
        while (adding) {
            System.out.println("Add regular topping? 1)LETTUCE 2)TOMATO 3)PICKLES 4)ONION 5)Done");
            String t = scanner.nextLine().trim();
            switch (t) {
                case "1" -> burger.addTopping(RegularTopping.LETTUCE);
                case "2" -> burger.addTopping(RegularTopping.TOMATO);
                case "3" -> burger.addTopping(RegularTopping.PICKLES);
                case "4" -> burger.addTopping(RegularTopping.ONION);
                default -> adding = false;
            }
        }

        // Premium toppings
        boolean addingP = true;
        while (addingP) {
            System.out.println("Add premium topping? 1)CHEESE 2)BACON 3)EXTRA_PATTY 4)AVOCADO 5)Done");
            String t = scanner.nextLine().trim();
            switch (t) {
                case "1" -> burger.addTopping(PremiumTopping.CHEESE);
                case "2" -> burger.addTopping(PremiumTopping.BACON);
                case "3" -> burger.addTopping(PremiumTopping.EXTRA_PATTY);
                case "4" -> burger.addTopping(PremiumTopping.AVOCADO);
                default -> addingP = false;
            }
        }

        // Mark extras
        boolean markExtra = true;
        while (markExtra) {
            System.out.println("Mark a topping as EXTRA (50% surcharge)? 1)CHEESE 2)BACON 3)EXTRA_PATTY 4)AVOCADO 5)LETTUCE 6)TOMATO 7)Done");
            String t = scanner.nextLine().trim();
            switch (t) {
                case "1" -> burger.markExtra(PremiumTopping.CHEESE);
                case "2" -> burger.markExtra(PremiumTopping.BACON);
                case "3" -> burger.markExtra(PremiumTopping.EXTRA_PATTY);
                case "4" -> burger.markExtra(PremiumTopping.AVOCADO);
                case "5" -> burger.markExtra(RegularTopping.LETTUCE);
                case "6" -> burger.markExtra(RegularTopping.TOMATO);
                default -> markExtra = false;
            }
        }

        System.out.println("Animal style? (adds $1.50) 1)Yes 2)No");
        String as = scanner.nextLine().trim();
        if (as.equals("1")) burger.setAnimalStyle(true);

        System.out.print("Quantity: ");
        int qty = readInt(1);

        order.addItem(new OrderItem(burger, qty));
        System.out.println("Added: " + qty + " x " + burger.getName());
    }

    private static void addDrinkFlow(Order order) {
        System.out.println("Choose drink: 1)COKE 2)SPRITE 3)ICED_TEA");
        String d = scanner.nextLine().trim();
        DrinkType dt = d.equals("2") ? DrinkType.SPRITE : d.equals("3") ? DrinkType.ICED_TEA : DrinkType.COKE;

        System.out.println("Size: 1)SINGLE 2)DOUBLE 3)TRIPLE");
        String s = scanner.nextLine().trim();
        Size size = s.equals("2") ? Size.DOUBLE : s.equals("3") ? Size.TRIPLE : Size.SINGLE;

        Drink drink = new Drink(dt, size);
        System.out.print("Quantity: ");
        int qty = readInt(1);
        order.addItem(new OrderItem(drink, qty));
        System.out.println("Added: " + qty + " x " + drink);
    }

    private static void addSideFlow(Order order) {
        System.out.println("Choose side: 1)Fries (2.50) 2)Loaded Fries (4.50) 3)Onion Rings (3.00)");
        String s = scanner.nextLine().trim();
        Side side;
        switch (s) {
            case "2" -> side = new Side("Loaded Fries", 4.50);
            case "3" -> side = new Side("Onion Rings", 3.00);
            default -> side = new Side("Fries (Regular)", 2.50);
        }
        System.out.print("Quantity: ");
        int qty = readInt(1);
        order.addItem(new OrderItem(side, qty));
        System.out.println("Added: " + qty + " x " + side);
    }

    private static double readDouble(double defaultVal) {
        try {
            String s = scanner.nextLine().trim();
            if (s.isEmpty()) return defaultVal;
            return Double.parseDouble(s);
        } catch (Exception e) {
            return defaultVal;
        }
    }

    private static int readInt(int defaultVal) {
        try {
            String s = scanner.nextLine().trim();
            if (s.isEmpty()) return defaultVal;
            return Integer.parseInt(s);
        } catch (Exception e) {
            return defaultVal;
        }
    }
}
