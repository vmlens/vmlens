package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public interface ActionContext {
    void afterInterleaveActionWithPositionFactory(RuntimeEvent runtimeEvent,
                                                  ThreadLocalDataWhenInTest threadLocalDataWhenInTest);

    RunState current();

    // rename new thread started
    RunState threadStarted(RunnableOrThreadWrapper startedThread, ThreadLocalDataWhenInTest threadLocalDataWhenInTest);

    int threadIndexForId(long threadId);

}
