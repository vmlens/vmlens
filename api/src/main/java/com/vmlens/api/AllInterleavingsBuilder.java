package com.vmlens.api;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;

public class AllInterleavingsBuilder {

    public static final int MAXIMUM_ITERATIONS = 100;
    public static final int REMOVE_CYCLE_THRESHOLD = 10;
    public static final int REPORT_AS_SUMMARY_THRESHOLD = 200;

    private int maximumIterations = MAXIMUM_ITERATIONS;
    private int removeCycleThreshold = REMOVE_CYCLE_THRESHOLD;
    private int reportAsSummaryThreshold = REPORT_AS_SUMMARY_THRESHOLD;
    private final List<AbstractMap.SimpleImmutableEntry<String,String>> intentionalDataRaces =
            new LinkedList<>();

    public AllInterleavingsBuilder withMaximumIterations(int newValue) {
        maximumIterations = newValue;
        return this;
    }

    public AllInterleavingsBuilder withRemoveCycleThreshold(int newValue) {
        removeCycleThreshold = newValue;
        return this;
    }

    public AllInterleavingsBuilder withReportAsSummaryThreshold(int newValue) {
        reportAsSummaryThreshold = newValue;
        return this;
    }

    public AllInterleavingsBuilder withIntentionalDataRace(String className, String fieldName) {
        intentionalDataRaces.add(new AbstractMap.SimpleImmutableEntry<>(className.replace('.', '/'),fieldName));
        return this;
    }

    public AllInterleavings build(String name) {
        return new AllInterleavings(name , false,
                maximumIterations,
                removeCycleThreshold,
                50,
                100,
                reportAsSummaryThreshold,
                intentionalDataRaces);
    }

}
