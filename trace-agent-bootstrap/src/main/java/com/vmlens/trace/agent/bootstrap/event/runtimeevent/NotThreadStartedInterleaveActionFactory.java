package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

public interface NotThreadStartedInterleaveActionFactory extends InterleaveActionFactory {

    @Override
    default void setStartedThreadIndex(int startedThreadIndex) {

    }
}
