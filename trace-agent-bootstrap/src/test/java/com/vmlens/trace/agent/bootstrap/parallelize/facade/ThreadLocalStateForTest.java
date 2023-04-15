package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;

public class ThreadLocalStateForTest implements ThreadLocalStateForFacade {

    private final long threadId;
    private ParallelizedThreadLocal parallelizedThreadLocal;


    public ThreadLocalStateForTest(long threadId) {
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
