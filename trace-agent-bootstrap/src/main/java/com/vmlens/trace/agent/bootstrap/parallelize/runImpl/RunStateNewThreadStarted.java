package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadStartFactory;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;


public class RunStateNewThreadStarted implements RunState {

    private final RunnableOrThreadWrapper startedThread;
    private final TestThreadState testThreadState;
    public RunStateNewThreadStarted(RunnableOrThreadWrapper startedThread, TestThreadState testThreadState) {
        this.startedThread = startedThread;
        this.testThreadState = testThreadState;
    }
    @Override
    public boolean isActive(TestThreadState testThreadState) {
        return false;
    }

    @Override
    public RunState after(ParallelizeAction action, TestThreadState testThreadState) {
        return this;
    }
    @Override
    public  boolean isNewTestTask(RunnableOrThreadWrapper newWrapper) {
       return startedThread.isSame(newWrapper);
    }

    @Override
    public void addTaskStartedInterleaveAction(TestThreadState beginTestThreadState, ActualRun calculatedRun) {
        calculatedRun.after(new ThreadStartFactory(testThreadState.threadIndex(), beginTestThreadState.threadIndex()));
    }
}
