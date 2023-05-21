package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizedThreadLocal;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWrapper;

public class ThreadLocalWrapperMock implements ThreadLocalWrapper  {

    private final long threadId;
    private ParallelizedThreadLocal parallelizedThreadLocal;

    public ThreadLocalWrapperMock(long threadId) {
        this.threadId = threadId;
    }

    @Override
    public long threadId() {
        return threadId;
    }

    @Override
    public void setParallelizedThreadLocal(ParallelizedThreadLocal parallelizedThreadLocal) {
        this.parallelizedThreadLocal = parallelizedThreadLocal;
    }

    @Override
    public ParallelizedThreadLocal getParallelizedThreadLocal() {
        return parallelizedThreadLocal;
    }

    public boolean isActive() {
        if(parallelizedThreadLocal == null) {
             return false;
        }

        return parallelizedThreadLocal.getRun().isActive(new TestThreadState(this));
    }

}
