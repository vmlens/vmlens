package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;

/**
 * null object design pattern
 */
public class RunStateEnd implements RunState {
    @Override
    public boolean isActive(ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        return true;
    }

    @Override
    public boolean isNewTestTask(RunnableOrThreadWrapper newWrapper) {
        return false;
    }

    @Override
    public void addTaskStartedInterleaveAction(ThreadLocalDataWhenInTest threadLocalDataWhenInTest, ActualRun calculatedRun) {
        throw new RuntimeException("should not be called");
    }

    @Override
    public RunState after(ParallelizeAction action, ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        return this;
    }
}
