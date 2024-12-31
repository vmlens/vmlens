package com.vmlens.trace.agent.bootstrap.event;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveInfo;

public interface InterleaveActionFactory extends RuntimeEvent {

    InterleaveAction create();

    @Override
    default RuntimeEvent after(ActualRun actualRun) {
        InterleaveInfo interleaveInfo = actualRun.after(create());
        setRunPosition(interleaveInfo.runPosition());
        return this;
    }

    @Override
    default boolean isInterleaveActionFactory() {
        return true;
    }
}
