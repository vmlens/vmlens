package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public interface ActionContext {

    RunState current();

    // rename new thread started
    RunState threadStarted(RunnableOrThreadWrapper startedThread, int startedThreadIndex);

    int getThreadIndexForNewTestThread();

}
