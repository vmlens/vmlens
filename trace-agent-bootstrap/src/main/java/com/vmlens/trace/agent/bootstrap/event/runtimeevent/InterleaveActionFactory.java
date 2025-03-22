package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveInfo;

public interface InterleaveActionFactory extends RuntimeEvent {

    InterleaveAction create(CreateInterleaveActionContext context);

    @Override
    default RuntimeEvent after(ActualRun actualRun, CreateInterleaveActionContext context) {
        InterleaveInfo interleaveInfo = actualRun.after(create(context));
        setRunPosition(interleaveInfo.runPosition());
        return this;
    }

    @Override
    default boolean isInterleaveActionFactory() {
        return true;
    }
}
