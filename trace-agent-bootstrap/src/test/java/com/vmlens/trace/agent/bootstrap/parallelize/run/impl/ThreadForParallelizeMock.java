package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadForParallelize;

public class ThreadForParallelizeMock extends ThreadForParallelize {

    private final long threadId;
    private final String threadName;

    public ThreadForParallelizeMock(long threadId, String threadName) {
        super(null);
        this.threadId = threadId;
        this.threadName = threadName;
    }

    @Override
    public boolean isBlocked() {
        return false;
    }

    @Override
    public long getId() {
        return threadId;
    }

    @Override
    public String getName() {
        return threadName;
    }
}
