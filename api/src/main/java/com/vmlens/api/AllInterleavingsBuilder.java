package com.vmlens.api;

import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;

/**
 * Use AllInterleavingsBuilder to configure {@link com.vmlens.api.AllInterleavings AllInterleavings}
 * <p>
 * Example, set the maximum iterations to 200:
 * <pre>{@code
    try (AllInterleavings allInterleavings = new AllInterleavingsBuilder()
        .withMaximumIterations(200)
        .build("testGrowableArrayBlockingQueueWithResize")) {
    }
 * }</pre>
 *
 */
public class AllInterleavingsBuilder {

    public static final int MAXIMUM_ITERATIONS = 100;
    public static final int REPORT_AS_SUMMARY_THRESHOLD = 200;

    private int maximumIterations = MAXIMUM_ITERATIONS;
    private int reportAsSummaryThreshold = REPORT_AS_SUMMARY_THRESHOLD;
    private boolean traceInterleaveActions;
    private final List<AbstractMap.SimpleImmutableEntry<String,String>> intentionalDataRaces =
            new LinkedList<>();

    /**
     * Sets the maximum number of iterations. Default value is 100.
     *
     * @param newValue The new value
     */
    public AllInterleavingsBuilder withMaximumIterations(int newValue) {
        maximumIterations = newValue;
        return this;
    }

    /**
     * Sets the threshold for generating a summary report instead of a normal execution trace report.
     * The normal execution trace report shows the interleaving of all synchronization actions for one run.
     * Since there might be too many synchronization actions to display them on a HTML page, after
     * reaching this threshold only a summary is shown. Default value is 200
     *
     * @see <a href="https://vmlens.com/docs/report/executiontrace/">Execution Trace</a>
     * @see <a href="https://vmlens.com/docs/report/executionsummary">Execution Summary</a>
     *
     * @param newValue The new value
     */
    public AllInterleavingsBuilder withReportAsSummaryThreshold(int newValue) {
        reportAsSummaryThreshold = newValue;
        return this;
    }


    /**
     * Let you define a benign data race. VMLens automatically detects all data races and reports them as a build error.
     * But for special high performance use cases data races are used. The jdk classes like java.lang.String for example use
     * benign data races to cache values. The java.langString for example uses the field hashCode to cache the calculated
     * hashCode.
     * <p>
     * Example:
     * <pre>{@code
            try(AllInterleavings allInterleavings = new AllInterleavingsBuilder()
                .withIntentionalDataRace("com.vmlens.inttest.nonvolatile.TestIntentionalDataRace" , "j")
                .build("intentionalDataRace")) {
            }
     * }</pre>
     *
     * @param className The full qualified name of the class
     * @param fieldName The name of the field
     */
    public AllInterleavingsBuilder withIntentionalDataRace(String className, String fieldName) {
        intentionalDataRaces.add(new AbstractMap.SimpleImmutableEntry<>(className.replace('.', '/'),fieldName));
        return this;
    }

    /**
     * For debugging. Writes the recorded interleave actions to agentlog.txt in the report directory.
     */
    public AllInterleavingsBuilder withTraceInterleaveActions() {
        traceInterleaveActions = true;
        return this;
    }

    /**
     *
     * Builds the AllInterleavings object.
     * @param name The name shown in the report.
     * @return the configured AllInterleavings object
     */
    public AllInterleavings build(String name) {
        return new AllInterleavings(name , false,
                maximumIterations,
                50,
                100,
                reportAsSummaryThreshold,
                traceInterleaveActions,
                intentionalDataRaces);
    }

}
