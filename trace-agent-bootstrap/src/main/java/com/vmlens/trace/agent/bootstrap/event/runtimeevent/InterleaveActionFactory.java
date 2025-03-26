package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveInfo;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public interface InterleaveActionFactory extends RuntimeEvent {

    InterleaveAction create(CreateInterleaveActionContext context);

    @Override
    default RuntimeEvent after(ActualRun actualRun,
                               CreateInterleaveActionContext context,
                               ThreadLocalWhenInTestForParallelize threadLocalWhenInTestForParallelize) {
        InterleaveInfo interleaveInfo = actualRun.after(create(context));
        setRunPosition(interleaveInfo.runPosition());
        setMethodCounter(threadLocalWhenInTestForParallelize);
        return this;
    }

    @Override
    default boolean isInterleaveActionFactory() {
        return true;
    }
}
