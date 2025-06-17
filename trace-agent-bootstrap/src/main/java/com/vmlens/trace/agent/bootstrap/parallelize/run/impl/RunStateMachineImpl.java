package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.AfterContext;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.*;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate.RunStateActive;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate.RunStateEnd;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public class RunStateMachineImpl implements RunStateMachine  {

    private RunState currentState;

    public RunStateMachineImpl(RunStateContext runStateContext) {
        this.currentState = new RunStateActive(runStateContext);
    }

    @Override
    public void after(AfterContext afterContext, SendEvent sendEvent) {
        currentState = currentState.after(afterContext,sendEvent);
    }

    @Override
    public ThreadLocalWhenInTest processNewTestTask(NewTaskContext newTaskContext, Run run, SendEvent sendEvent) {
        RunStateAndResult<ThreadLocalWhenInTest> result = currentState.processNewTestTask(newTaskContext, run, sendEvent);
        currentState = result.runState();
        return result.result();
    }

    public void newTestTaskStarted(ThreadWrapper newWrapper) {
        currentState = currentState.newTestTaskStarted(newWrapper);
    }

    @Override
    public ActualRun end(ThreadLocalForParallelize threadLocalForParallelize) {
        threadLocalForParallelize.setParallelizedThreadLocalToNull();
        ActualRun actualRun = currentState.actualRun();
        currentState = new RunStateEnd();
        return actualRun;
    }

    @Override
    public boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,SendEvent sendEvent) {
        return currentState.isActive(threadLocalDataWhenInTest,sendEvent);
    }

    @Override
    public boolean checkStopWaiting(SendEvent sendEvent) {
        RunStateAndResult<Boolean> result = currentState.checkBlocked(sendEvent);
        currentState = result.runState();
        return result.result();
    }
}
