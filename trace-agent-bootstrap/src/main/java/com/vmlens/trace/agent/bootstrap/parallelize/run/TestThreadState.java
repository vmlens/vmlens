package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.actionImpl.ParallelizeActionForThreadStart;

public class TestThreadState {
    private final ThreadLocalWrapperForParallelize threadLocalWrapperForParallelize;

    public TestThreadState(ThreadLocalWrapperForParallelize threadLocalWrapperForParallelize) {
        this.threadLocalWrapperForParallelize = threadLocalWrapperForParallelize;
    }

    public void createNewParallelizedThreadLocal(Run run, int threadIndex) {
        threadLocalWrapperForParallelize.setParallelizedThreadLocal(new ParallelizedThreadLocal(run, threadIndex));
    }

    public void setParallelizedThreadLocalToNull() {
        threadLocalWrapperForParallelize.setParallelizedThreadLocal(null);
    }

    public long threadId() {
        return threadLocalWrapperForParallelize.threadId();
    }

    public int threadIndex() {
        return threadLocalWrapperForParallelize.getParallelizedThreadLocal().threadIndex();
    }

    public void after(ParallelizeAction action) {
        if (threadLocalWrapperForParallelize.getParallelizedThreadLocal() != null) {
            threadLocalWrapperForParallelize.getParallelizedThreadLocal().after(action, this);
        }
    }

    public void addCreatedThreadForAfterStart(RunnableOrThreadWrapper runnableOrThreadWrapper) {
        if (threadLocalWrapperForParallelize.getParallelizedThreadLocal() != null) {
            threadLocalWrapperForParallelize.getParallelizedThreadLocal().setCreatedThread(runnableOrThreadWrapper);
        }
    }

    public void afterThreadStart() {
        if (threadLocalWrapperForParallelize.getParallelizedThreadLocal() != null) {
            threadLocalWrapperForParallelize.getParallelizedThreadLocal()
                    .after(new ParallelizeActionForThreadStart(threadLocalWrapperForParallelize.getParallelizedThreadLocal().createdThread()), this);
        }
    }

    public ThreadLocalWrapperForParallelize threadLocalWrapper() {
        return threadLocalWrapperForParallelize;
    }
}
