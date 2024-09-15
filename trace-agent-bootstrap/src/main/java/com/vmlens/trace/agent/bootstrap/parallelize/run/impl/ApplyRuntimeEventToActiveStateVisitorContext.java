package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;

public interface ApplyRuntimeEventToActiveStateVisitorContext {

    RunState current();

    // rename new thread started
    RunState threadStarted(RunnableOrThreadWrapper startedThread, int startedThreadIndex);

    int getThreadIndexForNewTestThread();

}
