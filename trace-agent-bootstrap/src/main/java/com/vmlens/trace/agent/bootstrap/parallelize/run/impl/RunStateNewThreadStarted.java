package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.event.impl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;


public class RunStateNewThreadStarted implements RunState {

    private final RunnableOrThreadWrapper startedThread;
    private final int startingThreadIndex;
    private final ThreadLocalDataWhenInTestMap runContext;

    public RunStateNewThreadStarted(RunnableOrThreadWrapper startedThread, int startingThreadIndex, ThreadLocalDataWhenInTestMap runContext) {
        this.startedThread = startedThread;
        this.startingThreadIndex = startingThreadIndex;
        this.runContext = runContext;
    }

    @Override
    public boolean isActive(ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        return false;
    }

    @Override
    public RunState after(ParallelizeAction action, ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        return this;
    }

    @Override
    public boolean isNewTestTask(RunnableOrThreadWrapper newWrapper) {
        return startedThread.isSame(newWrapper);
    }

    @Override
    public void addTaskStartedInterleaveAction(ThreadLocalDataWhenInTest threadLocalDataWhenInTest, ActualRun calculatedRun) {
        calculatedRun.after(
                new ThreadStartEvent(),
                new ActualRunContextImpl(runContext, threadLocalDataWhenInTest));
    }

}
