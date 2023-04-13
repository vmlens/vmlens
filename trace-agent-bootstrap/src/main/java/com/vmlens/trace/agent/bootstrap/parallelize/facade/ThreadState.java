package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.parallelize.loop.LoopThreadState;

public interface ThreadState extends LoopThreadState {
    ParallelizedThreadLocal getParallelizedThreadLocal();
    long threadId();
}
