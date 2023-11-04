package com.vmlens.trace.agent.bootstrap.event;

public interface SendEventContext {

    int runId();

    ThreadLocalWrapperForEvent threadLocalWrapper();

}
