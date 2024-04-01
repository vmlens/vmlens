package com.vmlens.trace.agent.bootstrap.parallelize.run;


import com.vmlens.trace.agent.bootstrap.callback.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.StaticEvent;
import com.vmlens.trace.agent.bootstrap.event.ThreadLocalWrapperForEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

/**
 * Set when the current thread is in a vmlens unit test
 */

public class ThreadLocalDataWhenInTest extends PerThreadCounter implements ThreadLocalWrapperForEvent {
    private final Run run;
    private final int threadIndex;
    private final QueueIn queueIn;
    private final long threadId;
    private boolean inCallbackProcessing = false;
    private RunnableOrThreadWrapper createdThread;

    public ThreadLocalDataWhenInTest(Run run, int threadIndex, QueueIn queueIn, long threadId) {
        this.run = run;
        this.threadIndex = threadIndex;
        this.queueIn = queueIn;
        this.threadId = threadId;
    }

    public void after(ParallelizeAction action) {
        run.after(action, this);
    }

    // public for test
    public int threadIndex() {
        return threadIndex;
    }

    // visible for test
    public Run getRun() {
        return run;
    }


    public RunnableOrThreadWrapper createdThread() {
        return createdThread;
    }

    public void setCreatedThread(RunnableOrThreadWrapper createdThread) {
        this.createdThread = createdThread;
    }

    public ThreadLocalDataWhenInTest startCallbackProcessing() {
        if (!inCallbackProcessing) {
            inCallbackProcessing = true;
            return this;
        }
        return null;
    }

    public void stopCallbackProcessing() {
        inCallbackProcessing = false;
    }

    @Override
    public long threadId() {
        return threadId;
    }

    @Override
    public void offer(StaticEvent element) {
        queueIn.offer(element);
    }
}
