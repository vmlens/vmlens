package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadStartFactory;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactoryAndRuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;


public class RunStateNewThreadStarted implements RunState {

    private final RunnableOrThreadWrapper startedThread;
    private final int startingThreadIndex;
    private final RunContext runContext;

    public RunStateNewThreadStarted(RunnableOrThreadWrapper startedThread, int startingThreadIndex, RunContext runContext) {
        this.startedThread = startedThread;
        this.startingThreadIndex = startingThreadIndex;
        this.runContext = runContext;
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
        calculatedRun.after(new InterleaveActionWithPositionFactoryAndRuntimeEvent(
                new ThreadStartFactory(startingThreadIndex,
                        beginTestThreadState.threadIndex()), new ThreadStartEvent()), new ActualRunContextImpl(runContext, beginTestThreadState));
    }

}
