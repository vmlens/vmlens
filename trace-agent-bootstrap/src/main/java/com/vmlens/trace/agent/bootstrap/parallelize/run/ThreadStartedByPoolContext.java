package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ThreadStartEvent;

public class ThreadStartedByPoolContext {

    private final Object pool;
    private final Runnable task;
    private final Thread startedThread;
    private final ThreadLocalWhenInTest threadLocalDataWhenInTest;
    private final QueueIn queueIn;
    private final ThreadStartEvent threadStartEvent;

    public ThreadStartedByPoolContext(Object pool,
                                      Runnable task,
                                      Thread startedThread,
                                      ThreadLocalWhenInTest threadLocalDataWhenInTest,
                                      QueueIn queueIn,
                                      ThreadStartEvent threadStartEvent) {
        this.pool = pool;
        this.task = task;
        this.startedThread = startedThread;
        this.threadLocalDataWhenInTest = threadLocalDataWhenInTest;
        this.queueIn = queueIn;
        this.threadStartEvent = threadStartEvent;
    }

    public Object pool() {
        return pool;
    }

    public Runnable task() {
        return task;
    }

    public Thread startedThread() {
        return startedThread;
    }

    public ThreadLocalWhenInTest threadLocalDataWhenInTest() {
        return threadLocalDataWhenInTest;
    }

    public QueueIn queueIn() {
        return queueIn;
    }

    public ThreadStartEvent threadStartEvent() {
        return threadStartEvent;
    }
}
