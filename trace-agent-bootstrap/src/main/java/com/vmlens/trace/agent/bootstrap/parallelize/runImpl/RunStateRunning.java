package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ActionContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;

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
    public boolean isActive(ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        return calculatedRun.isActive(threadLocalDataWhenInTest.threadIndex());
    }

    @Override
    public RunState after(ParallelizeAction action, ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        action.addInterleaveActionAndOrEvent(this, threadLocalDataWhenInTest);
        return action.nextState(this, threadLocalDataWhenInTest);
    }

    @Override
    public RunState threadStarted(RunnableOrThreadWrapper startedThread, ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        return new RunStateNewThreadStarted(startedThread, threadLocalDataWhenInTest.threadIndex(), runContext);
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
    public void afterInterleaveActionWithPositionFactory(RuntimeEvent runtimeEvent,
                                                         ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        actualRun.after(runtimeEvent, new ActualRunContextImpl(runContext, threadLocalDataWhenInTest));
    }

    @Override
    public int threadIndexForId(long threadId) {
        return runContext.threadIndexForThreadId(threadId);
    }

}
