package com.vmlens.report.assertion;


public class EventForAssertion implements Comparable<EventForAssertion> {

    private final int runId;
    private final int loopId;
    private final int runPosition;
    private final EventForAssertionType eventForAssertionType;

    public EventForAssertion(int runId,
                             int loopId,
                             int runPosition,
                             EventForAssertionType eventForAssertionType) {
        this.runId = runId;
        this.loopId = loopId;
        this.runPosition = runPosition;
        this.eventForAssertionType = eventForAssertionType;
    }

    public EventForAssertionType eventForAssertionType() {
        return eventForAssertionType;
    }

    public int runId() {
        return runId;
    }

    public int loopId() {
        return loopId;
    }

    public int runPosition() {
        return runPosition;
    }

    @Override
    public int compareTo(EventForAssertion other) {
        if(loopId != other.loopId) {
            return Integer.compare(loopId,other.loopId);
        }
        if(runId != other.runId) {
            return Integer.compare(runId,other.runId);
        }
        return Integer.compare(runPosition,other.runPosition);
    }

    @Override
    public String toString() {
        return "EventForAssertion{" +
                "runId=" + runId +
                ", loopId=" + loopId +
                ", runPosition=" + runPosition +
                ", eventForAssertionType=" + eventForAssertionType +
                '}';
    }
}
