package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;


import com.vmlens.trace.agent.bootstrap.event.SendEventContext;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRunContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;

public class ActualRunContextImpl implements ActualRunContext {
    private final ThreadLocalDataWhenInTestMap runContext;
    private final ThreadLocalDataWhenInTest threadLocalDataWhenInTest;

    public ActualRunContextImpl(ThreadLocalDataWhenInTestMap runContext, ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        this.runContext = runContext;
        this.threadLocalDataWhenInTest = threadLocalDataWhenInTest;
    }

    @Override
    public SendEventContext sendEventContext() {
        return new SendEventContextImpl(runContext, threadLocalDataWhenInTest);
    }
}
