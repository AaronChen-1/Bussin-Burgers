package com.bussinburgers.enums;

public enum Size {
    SINGLE(0.0),
    DOUBLE(2.00),
    TRIPLE(4.00);

    private final double extra;

    Size(double extra) { this.extra = extra; }
    public double getExtra() { return extra; }
}
