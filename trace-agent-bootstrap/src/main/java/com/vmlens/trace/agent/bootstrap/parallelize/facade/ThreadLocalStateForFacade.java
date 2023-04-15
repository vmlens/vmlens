package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalState;

public interface ThreadLocalStateForFacade extends ThreadLocalState {
    ParallelizedThreadLocal getParallelizedThreadLocal();

    long threadId();
}
