package com.bussinburgers;

import com.bussinburgers.ui.UserInterface;

public class Main {
    public static void main(String[] args) {
        printWelcomeBanner();
        UserInterface ui = new UserInterface();
        ui.start();
    }
    private static void printWelcomeBanner() {
        System.out.println("=====================================");
        System.out.println("        WELCOME TO BUSSIN BURGERS     ");
        System.out.println("     \"The ultimate burger experience\"  ");
        System.out.println("=====================================");
        System.out.println();
    }
}

