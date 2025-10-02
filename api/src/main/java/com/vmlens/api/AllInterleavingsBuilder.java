package com.vmlens.api;

public class AllInterleavingsBuilder {

    public static final int MAXIMUM_ITERATIONS = 20;
    public static final int MAXIMUM_ALTERNATING_ORDERS = 10;

    private int maximumIterations = MAXIMUM_ITERATIONS;
    private int maximumAlternatingOrders = MAXIMUM_ALTERNATING_ORDERS;

    public AllInterleavingsBuilder withMaximumIterations(int newValue) {
        maximumIterations = newValue;
        return this;
    }

    public AllInterleavingsBuilder withMaximumAlternatingOrders(int newValue) {
        maximumAlternatingOrders = newValue;
        return this;
    }

    public AllInterleavings build(String name) {
        return new AllInterleavings(name , false,
                maximumIterations,
                maximumAlternatingOrders,
                500,
                5000 );
    }

}
