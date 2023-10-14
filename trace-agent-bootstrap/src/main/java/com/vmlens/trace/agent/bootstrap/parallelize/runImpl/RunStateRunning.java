package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactoryAndOrRuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ActionContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;

public class RunStateRunning implements RunState, ActionContext {
    private final ActualRun actualRun;
    private final CalculatedRun calculatedRun;
    private final RunContext runContext;

    public RunStateRunning(ActualRun actualRun, CalculatedRun calculatedRun, RunContext runContext) {
        this.actualRun = actualRun;
        this.calculatedRun = calculatedRun;
        this.runContext = runContext;
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
        action.addInterleaveActionAndOrEvent(this, testThreadState);
        return action.nextState(this, testThreadState);
    }

    @Override
    public RunState threadStarted(RunnableOrThreadWrapper startedThread, TestThreadState testThreadState) {
        return new RunStateNewThreadStarted(startedThread, testThreadState.threadIndex(), runContext);
    }

    @Override
    public boolean isNewTestTask(RunnableOrThreadWrapper newWrapper) {
        return false;
    }

    @Override
    public void addTaskStartedInterleaveAction(TestThreadState beginTestThreadState, ActualRun calculatedRun) {
        throw new RuntimeException("should not be called");
    }

    @Override
    public void afterInterleaveActionWithPositionFactory(InterleaveActionWithPositionFactoryAndOrRuntimeEvent interleaveActionWithPositionFactory,
                                                         TestThreadState testThreadState) {
        actualRun.after(interleaveActionWithPositionFactory, new ActualRunContextImpl(runContext, testThreadState));
    }

    @Override
    public int threadIndexForId(long threadId) {
        return runContext.threadIndexForThreadId(threadId);
    }

}
