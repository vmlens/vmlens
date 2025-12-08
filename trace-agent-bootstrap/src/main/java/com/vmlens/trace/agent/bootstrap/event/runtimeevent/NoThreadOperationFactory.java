package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

public interface NoThreadOperationFactory extends InterleaveActionFactoryAndRuntimeEvent {

    @Override
    default void update(ThreadCount threadCount) {
        // Nothing to do for if not thread start or join
    }
}
