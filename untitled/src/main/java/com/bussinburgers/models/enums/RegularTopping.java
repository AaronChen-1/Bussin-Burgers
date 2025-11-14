package com.bussinburgers.models.enums;

public enum RegularTopping {
    LETTUCE("Lettuce"),
    TOMATO("Tomato"),
    PICKLES("Pickles"),
    ONIONS("Onions"),
    GRILLED_ONIONS("Grilled Onions"),
    JALAPENOS("Jalapenos");

    private final String displayName;
    RegularTopping(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}