package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateAndRuntimeEvent;


public class RunStateNewThreadStarted implements RunState {

    private final RunnableOrThreadWrapper startedThread;
    private final ThreadLocalDataWhenInTestMap runContext;
    private final int startedThreadIndex;

    public RunStateNewThreadStarted(RunnableOrThreadWrapper startedThread, ThreadLocalDataWhenInTestMap runContext, int startedThreadIndex) {
        this.startedThread = startedThread;
        this.runContext = runContext;
        this.startedThreadIndex = startedThreadIndex;
    }

    @Override
    public boolean isActive(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return false;
    }

    @Override
    public RunStateAndRuntimeEvent after(RuntimeEvent runtimeEvent, ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        throw new RuntimeException("should not be called");
    }

    @Override
    public boolean isNewTestTask(RunnableOrThreadWrapper newWrapper) {
        return startedThread.isSame(newWrapper);
    }

    @Override
    public int getStartedThreadIndex() {
        return startedThreadIndex;
    }
}
