package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.*;

public class RunStateMachineImpl implements RunStateMachine {

    private final ThreadLocalDataWhenInTestMap runContext;
    private final ActualRun actualRun;
    private RunState stateAfterNewThreadStarted;
    private RunState currentState;

    public RunStateMachineImpl(ActualRun actualRun,
                               ThreadLocalDataWhenInTestMap runContext,
                               RunState initialState) {
        this.actualRun = actualRun;
        this.currentState = initialState;
        this.stateAfterNewThreadStarted = initialState;
        this.runContext = runContext;
    }

    @Override
    public boolean isActive(ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        return currentState.isActive(threadLocalDataWhenInTest);
    }

    @Override
    public void after(ParallelizeAction action, ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        currentState = currentState.after(action, threadLocalDataWhenInTest);

    }

    @Override
    public ThreadLocalDataWhenInTest processNewTestTask(RunnableOrThreadWrapper newWrapper,
                                                        ThreadLocalForParallelize threadLocalForParallelize, Run run) {
        if (!currentState.isNewTestTask(newWrapper)) {
            return null;
        }
        ThreadLocalDataWhenInTest threadLocalDataWhenInTest = runContext.create(
                run, threadLocalForParallelize.queueIn(), threadLocalForParallelize.threadId());
        currentState.addTaskStartedInterleaveAction(threadLocalDataWhenInTest, actualRun);
        currentState = stateAfterNewThreadStarted;
        threadLocalForParallelize.setThreadLocalDataWhenInTest(threadLocalDataWhenInTest);
        return threadLocalDataWhenInTest;
    }

    @Override
    public void setStateRecording() {
        currentState = new RunStateRecording(actualRun, runContext);
        stateAfterNewThreadStarted = currentState;
    }

    @Override
    public ActualRun end(ThreadLocalForParallelize threadLocalForParallelize) {
        threadLocalForParallelize.setParallelizedThreadLocalToNull();
        currentState = new RunStateEnd();
        return actualRun;
    }
}
