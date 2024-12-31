package com.vmlens.trace.agent.bootstrap.event;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;

public interface RuntimeEventOnly extends RuntimeEvent {

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
