package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ActionContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;

public class RunStateRunning implements RunState, ActionContext {
    private final ActualRun calculatedRun;
    private final ThreadIdToState threadIdToState;

    public RunStateRunning(ActualRun calculatedRun, ThreadIdToState threadIdToState) {
        this.calculatedRun = calculatedRun;
        this.threadIdToState = threadIdToState;
    }

    @Override
    public ActualRun getCalculatedRun() {
        return calculatedRun;
    }

    @Override
    public RunState current() {
        return this;
    }


    @Override
    public boolean isActive(TestThreadState testThreadState) {
        return calculatedRun.isActive(testThreadState.threadIndex());
    }

    @Override
    public RunState after(ParallelizeAction action, TestThreadState testThreadState) {
        action.addInterleaveAction(this, testThreadState);
        return action.nextState(this, testThreadState);
    }

    @Override
    public RunState threadStarted(RunnableOrThreadWrapper startedThread, TestThreadState testThreadState) {
        return new RunStateNewThreadStarted(startedThread, testThreadState);
    }

    @Override
    public boolean isNewTestTask(RunnableOrThreadWrapper newWrapper) {
        return false;
    }
    @Override
    public void addTaskStartedInterleaveAction(TestThreadState beginTestThreadState, ActualRun calculatedRun) {
        throw new RuntimeException("should not be called");
    }

}
