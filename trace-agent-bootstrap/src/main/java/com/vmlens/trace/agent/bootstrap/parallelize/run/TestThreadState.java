package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.actionImpl.ThreadStart;

public class TestThreadState {
    private final ThreadLocalWrapper threadLocalWrapper;

    public TestThreadState(ThreadLocalWrapper threadLocalWrapper) {
        this.threadLocalWrapper = threadLocalWrapper;
    }

    public void createNewParallelizedThreadLocal(Run run, int threadIndex) {
        threadLocalWrapper.setParallelizedThreadLocal(new ParallelizedThreadLocal(run, threadIndex));
    }
    public void setParallelizedThreadLocalToNull() {
        threadLocalWrapper.setParallelizedThreadLocal(null);
    }
    public long threadId() {
        return threadLocalWrapper.threadId();
    }

    public int threadIndex() {
        return threadLocalWrapper.getParallelizedThreadLocal().threadIndex();
    }

    public void after(ParallelizeAction action) {
        if (threadLocalWrapper.getParallelizedThreadLocal() != null) {
            threadLocalWrapper.getParallelizedThreadLocal().after(action, this);
        }
    }

    public void addCreatedThreadForAfterStart(RunnableOrThreadWrapper runnableOrThreadWrapper) {
        if (threadLocalWrapper.getParallelizedThreadLocal() != null) {
            threadLocalWrapper.getParallelizedThreadLocal().setCreatedThread(runnableOrThreadWrapper);
        }
    }

    public void afterThreadStart() {
        if (threadLocalWrapper.getParallelizedThreadLocal() != null) {
            threadLocalWrapper.getParallelizedThreadLocal()
                    .after(new ThreadStart(threadLocalWrapper.getParallelizedThreadLocal().createdThread()), this);
        }
    }
}
