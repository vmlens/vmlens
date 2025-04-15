package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public interface PluginEventOnly extends RuntimeEvent {

    @Override
    default  void after(InterleaveRun interleaveRun,
                        CreateInterleaveActionContext context,
                        ThreadLocalWhenInTestForParallelize threadLocalWhenInTestForParallelize,
                        SendEvent sendEvent) {
        setRunPosition(interleaveRun.actualRun().positionInRun());
        setMethodCounter(threadLocalWhenInTestForParallelize);
        sendEvent.sendSerializable(this);
    }

    @Override
    default boolean isInterleaveActionFactory() {
        return false;
    }

    @Override
    default void setStartedThreadIndex(int startedThreadIndex) {

    }
}
