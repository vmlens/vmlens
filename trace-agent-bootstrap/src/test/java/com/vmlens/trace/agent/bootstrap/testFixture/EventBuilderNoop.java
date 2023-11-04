package com.vmlens.trace.agent.bootstrap.testFixture;

public class EventBuilderNoop implements EventBuilder {
    @Override
    public void addVolatileAccessEvent(long threadId, int order, int fieldId, int methodCounter, int operation, long objectHashCode, int runId, int loopId, int slidingWindowId) {

    }
}
