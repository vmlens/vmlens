package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactoryAndOrRuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public interface ActionContext {
    void afterInterleaveActionWithPositionFactory(InterleaveActionWithPositionFactoryAndOrRuntimeEvent interleaveActionWithPositionFactoryAndOrRuntimeEvent,
                                                  TestThreadState testThreadState);

    RunState current();

    // rename new thread started
    RunState threadStarted(RunnableOrThreadWrapper startedThread, TestThreadState testThreadState);

    int threadIndexForId(long threadId);

}
