package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRun;
import com.vmlens.trace.agent.bootstrap.parallize.logic.RunnableOrThreadWrapper;

public interface ActionContext {
    InterleaveRun getCalculatedRun();
    RunState current();
    // rename new thread started
    RunState threadStarted(RunnableOrThreadWrapper startedThread, TestThreadState testThreadState);
}
