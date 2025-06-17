package com.vmlens.trace.agent.bootstrap.parallelize.run;

public class ThreadStartedByPoolContext {

    private final Object pool;
    private final Runnable task;
    private final Thread startedThread;

    public ThreadStartedByPoolContext(Object pool,
                                      Runnable task,
                                      Thread startedThread) {
        this.pool = pool;
        this.task = task;
        this.startedThread = startedThread;
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
}
