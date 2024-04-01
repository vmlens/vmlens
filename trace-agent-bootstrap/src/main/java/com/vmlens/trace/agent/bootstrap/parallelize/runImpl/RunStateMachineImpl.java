package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;

public class RunStateMachineImpl implements RunStateMachine {

    private final RunContext runContext;
    private final ActualRun actualRun;
    private RunState stateAfterNewThreadStarted;
    private RunState currentState;

    public RunStateMachineImpl(ActualRun actualRun,
                               RunContext runContext,
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
    public void processNewTestTask(ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        runContext.add(threadLocalDataWhenInTest);
        currentState.addTaskStartedInterleaveAction(threadLocalDataWhenInTest, actualRun);
        currentState = stateAfterNewThreadStarted;
    }

    @Override
    public boolean isNewTestTask(RunnableOrThreadWrapper newWrapper) {
        return currentState.isNewTestTask(newWrapper);
    }

    @Override
    public void setStateRecording() {
        currentState = new RunStateRecording(actualRun, runContext);
        stateAfterNewThreadStarted = currentState;
    }

    @Override
    public ActualRun end() {
        currentState = new RunStateEnd();
        return actualRun;
    }
}
