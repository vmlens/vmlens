package com.vmlens.trace.agent.bootstrap.event;

public interface RuntimeEventOnly extends RuntimeEvent {

    @Override
    default void accept(RuntimeEventVisitor runtimeEventVisitor) {
        runtimeEventVisitor.visit(this);
    }

    @Override
    default boolean isInterleaveActionFactory() {
        return false;
    }
}
