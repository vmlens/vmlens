package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;

public class RunStateMachineImpl implements RunStateMachine {

    private final ActualRun calculatedRun;
    // package visible for test
    final ThreadIdToState threadIdToState = new ThreadIdToState();
    private RunState runState;
    public RunStateMachineImpl(ActualRun calculatedRun, TestThreadState testThreadState) {
        this.calculatedRun = calculatedRun;
        this.runState = new RunStateRunning(calculatedRun,threadIdToState);
        threadIdToState.add(testThreadState);
    }
    @Override
    public boolean isActive(TestThreadState testThreadState) {
        return runState.isActive(testThreadState);
    }
    @Override
    public void after(ParallelizeAction action, TestThreadState testThreadState) {
        runState = runState.after(action, testThreadState);

    }
    @Override
    public void processNewTestTask(TestThreadState testThreadState) {
        threadIdToState.add(testThreadState);
        runState.addTaskStartedInterleaveAction(testThreadState,calculatedRun);
        runState = new RunStateRunning(calculatedRun,threadIdToState);
    }
    @Override
    public boolean isNewTestTask(RunnableOrThreadWrapper newWrapper) {
        return runState.isNewTestTask(newWrapper);
    }
    @Override
    public void end() {
        runState = new RunStateEnd();
    }
}
