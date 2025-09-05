package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveInfo;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public interface InterleaveActionFactory extends RuntimeEvent {

    InterleaveAction create(CreateInterleaveActionContext context);

    @Override
    default void after(InterleaveRun interleaveRun,
                  CreateInterleaveActionContext context,
                  ThreadLocalWhenInTestForParallelize threadLocalWhenInTestForParallelize,
                  SendEvent sendEvent)  {
        InterleaveInfo interleaveInfo = interleaveRun.after(create(context));
        setRunPosition(interleaveInfo.runPosition());
        setMethodCounter(threadLocalWhenInTestForParallelize);
        sendEvent.sendSerializable(this);
    }

    @Override
    default boolean isInterleaveActionFactory() {
        return true;
    }
}
