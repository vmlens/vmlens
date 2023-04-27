package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;
import com.vmlens.trace.agent.bootstrap.parallize.logic.RunnableOrThreadWrapper;

/**
 * null object design pattern
 */
public class RunStateEnd implements RunState {
    @Override
    public boolean isActive(TestThreadState testThreadState) {
        return true;
    }
    @Override
    public boolean isNewTestTask(RunnableOrThreadWrapper newWrapper) {
        return false;
    }
    @Override
    public void addTaskStartedInterleaveAction(TestThreadState beginTestThreadState, InterleaveRun calculatedRun) {
        throw new RuntimeException("should not be called");
    }
    @Override
    public RunState after(ParallelizeAction action, TestThreadState testThreadState) {
        return this;
    }
}
