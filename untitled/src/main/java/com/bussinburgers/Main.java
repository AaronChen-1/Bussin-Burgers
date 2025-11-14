package com.bussinburgers;

import com.bussinburgers.ui.UserInterface;

public class Main {
    public static void main(String[] args) {
        printWelcomeBanner();
        UserInterface ui = new UserInterface();
        ui.start();
    }
    private static void printWelcomeBanner() {
        System.out.println("=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿");
        System.out.println("        \uD835\uDD4E\uD835\uDD56\uD835\uDD5D\uD835\uDD54\uD835\uDD60\uD835\uDD5E\uD835\uDD56 \uD835\uDD65\uD835\uDD60 \uD835\uDD39\uD835\uDD66\uD835\uDD64\uD835\uDD64\uD835\uDD5A\uD835\uDD5F \uD835\uDD39\uD835\uDD66\uD835\uDD63\uD835\uDD58\uD835\uDD56\uD835\uDD63\uD835\uDD64     ");
        System.out.println("     \uD835\uDD4B\uD835\uDD59\uD835\uDD56 \uD835\uDD66\uD835\uDD5D\uD835\uDD65\uD835\uDD5A\uD835\uDD5E\uD835\uDD52\uD835\uDD65\uD835\uDD56 \uD835\uDD53\uD835\uDD66\uD835\uDD63\uD835\uDD58\uD835\uDD56\uD835\uDD63 \uD835\uDD56\uD835\uDD69\uD835\uDD61\uD835\uDD56\uD835\uDD63\uD835\uDD5A\uD835\uDD56\uD835\uDD5F\uD835\uDD54\uD835\uDD56  ");
        System.out.println("=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿=̿");
        System.out.println();
    }
}

