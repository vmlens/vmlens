package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;

public interface PluginEventOnly extends RuntimeEvent {

    @Override
    default RuntimeEvent after(ActualRun actualRun) {
        setRunPosition(actualRun.positionInRun());
        return this;
    }

    @Override
    default boolean isInterleaveActionFactory() {
        return false;
    }
}
