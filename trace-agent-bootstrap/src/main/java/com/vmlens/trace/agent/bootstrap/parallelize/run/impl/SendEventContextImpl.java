package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.event.SendEventContext;
import com.vmlens.trace.agent.bootstrap.event.ThreadLocalWrapperForEvent;

public class SendEventContextImpl implements SendEventContext {

    private final ThreadLocalDataWhenInTestMap runContext;
    private final ThreadLocalWrapperForEvent threadLocalWrapperForParallelize;

    public SendEventContextImpl(ThreadLocalDataWhenInTestMap runContext, ThreadLocalWrapperForEvent threadLocalWrapperForParallelize) {
        this.runContext = runContext;
        this.threadLocalWrapperForParallelize = threadLocalWrapperForParallelize;
    }

    public ThreadLocalWrapperForEvent threadLocalWrapper() {
        return threadLocalWrapperForParallelize;
    }

    @Override
    public int runId() {
        return runContext.runId();
    }

    public int loopId() {
        return runContext.loopId();
    }
}
