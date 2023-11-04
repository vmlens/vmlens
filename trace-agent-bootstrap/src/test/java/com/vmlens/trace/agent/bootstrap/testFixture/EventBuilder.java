package com.vmlens.trace.agent.bootstrap.testFixture;

public interface EventBuilder {

    void addVolatileAccessEvent(long threadId, int order, int fieldId, int methodId, int operation,
                                long objectHashCode, int runId, int loopId, int slidingWindowId);
}
