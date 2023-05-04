package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public interface ActionContext {
    ActualRun getCalculatedRun();
    RunState current();
    // rename new thread started
    RunState threadStarted(RunnableOrThreadWrapper startedThread, TestThreadState testThreadState);
}
