package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.parallelize.loop.LoopThreadState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;

public class ThreadStateForTest implements ThreadState {

    private final long threadId;
    private ParallelizedThreadLocal parallelizedThreadLocal;


    public ThreadStateForTest(long threadId) {
        this.threadId = threadId;
    }

    @Override
    public void createNewParallelizedThreadLocal(Run run) {
        parallelizedThreadLocal = new ParallelizedThreadLocal(run);
    }

    @Override
    public void setParallelizedThreadLocalToNull() {
        parallelizedThreadLocal = null;
    }

    @Override
    public ParallelizedThreadLocal getParallelizedThreadLocal() {
        return parallelizedThreadLocal;
    }

    @Override
    public long threadId() {
        return threadId;
    }
}
