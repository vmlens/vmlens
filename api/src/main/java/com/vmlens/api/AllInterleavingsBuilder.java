package com.vmlens.api;

public class AllInterleavingsBuilder {

    public static final int MAXIMUM_ITERATIONS = 100;
    public static final int MAXIMUM_ALTERNATING_ORDERS = 25;
    public static final int REPORT_AS_SUMMARY_THRESHOLD = 200;

    private int maximumIterations = MAXIMUM_ITERATIONS;
    private int maximumAlternatingOrders = MAXIMUM_ALTERNATING_ORDERS;
    private int reportAsSummaryThreshold = REPORT_AS_SUMMARY_THRESHOLD;

    public AllInterleavingsBuilder withMaximumIterations(int newValue) {
        maximumIterations = newValue;
        return this;
    }

    public AllInterleavingsBuilder withMaximumAlternatingOrders(int newValue) {
        maximumAlternatingOrders = newValue;
        return this;
    }

    public AllInterleavingsBuilder withReportAsSummaryThreshold(int newValue) {
        reportAsSummaryThreshold = newValue;
        return this;
    }

    public AllInterleavings build(String name) {
        return new AllInterleavings(name , false,
                maximumIterations,
                maximumAlternatingOrders,
                500,
                5000,
                reportAsSummaryThreshold);
    }

}
