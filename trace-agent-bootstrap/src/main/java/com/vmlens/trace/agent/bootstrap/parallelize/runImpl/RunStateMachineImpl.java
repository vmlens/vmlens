package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;

public class RunStateMachineImpl implements RunStateMachine {
    // package visible for test
    final RunContext runContext;
    private final ActualRun actualRun;
    private final InterleaveLoop interleaveLoop;
    private RunState stateAfterNewThreadStarted;
    private RunState currentState;

    public RunStateMachineImpl(ActualRun actualRun,
                               TestThreadState testThreadState,
                               InterleaveLoop interleaveLoop,
                               RunContext runContext,
                               RunState initialState) {
        this.actualRun = actualRun;
        this.currentState = initialState;
        this.stateAfterNewThreadStarted = initialState;
        this.runContext = runContext;
        this.interleaveLoop = interleaveLoop;
        runContext.add(testThreadState);
    }

    @Override
    public boolean isActive(TestThreadState testThreadState) {
        return currentState.isActive(testThreadState);
    }

    @Override
    public void after(ParallelizeAction action, TestThreadState testThreadState) {
        currentState = currentState.after(action, testThreadState);

    }
    @Override
    public void processNewTestTask(TestThreadState testThreadState) {
        runContext.add(testThreadState);
        currentState.addTaskStartedInterleaveAction(testThreadState, actualRun);
        currentState = stateAfterNewThreadStarted;
    }

    @Override
    public boolean isNewTestTask(RunnableOrThreadWrapper newWrapper) {
        return currentState.isNewTestTask(newWrapper);
    }

    @Override
    public void end() {
        interleaveLoop.addActualRun(actualRun);
        currentState = new RunStateEnd();
    }

    @Override
    public void setStateRecording() {
        currentState = new RunStateRecording(actualRun, runContext);
        stateAfterNewThreadStarted = currentState;
    }

    @Override
    public ActualRun actualRun() {
        return actualRun;
    }
}
