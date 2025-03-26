package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public interface PluginEventOnly extends RuntimeEvent {

    @Override
    default RuntimeEvent after(ActualRun actualRun,
                               CreateInterleaveActionContext context,
                               ThreadLocalWhenInTestForParallelize threadLocalWhenInTestForParallelize) {
        setRunPosition(actualRun.positionInRun());
        setMethodCounter(threadLocalWhenInTestForParallelize);
        return this;
    }

    @Override
    default boolean isInterleaveActionFactory() {
        return false;
    }
}
