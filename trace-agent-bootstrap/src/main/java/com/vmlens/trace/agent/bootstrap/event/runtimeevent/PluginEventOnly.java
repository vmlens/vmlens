package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

public interface PluginEventOnly extends RuntimeEvent {

    @Override
    default InterleaveActionFactoryAndRuntimeEvent asInterleaveActionFactory() {
        return null;
    }

    @Override
    default PluginEventOnly asPluginEventOnly() {
        return this;
    }

    int threadIndex();
}
