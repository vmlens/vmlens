package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.*;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestBlockedException;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstates.RunStateEnd;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestAndSerializableEvents;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public class RunStateMachineImpl implements RunStateMachine, ProcessRuntimeEventCallback {

    private final ThreadIndexAndThreadStateMap runContext;
    private final ActualRun actualRun;
    private RunState currentState;

    public RunStateMachineImpl(ActualRun actualRun,
                               ThreadIndexAndThreadStateMap runContext,
                               RunState initialState) {
        this.actualRun = actualRun;
        this.currentState = initialState;
        this.runContext = runContext;
    }

    @Override
    public boolean canProcessEndOfOperation(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        return currentState.isActive(threadLocalDataWhenInTest);
    }

    @Override
    public RuntimeEvent after(RuntimeEvent runtimeEvent, ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        RunStateAndResult<RuntimeEvent> result = currentState.after(this, runtimeEvent, threadLocalDataWhenInTest);
        currentState = result.runState();
        return result.result();
    }

    @Override
    public ThreadLocalWhenInTestAndSerializableEvents processNewTestTask(RunnableOrThreadWrapper newWrapper,
                                                                         ThreadLocalForParallelize threadLocalForParallelize, Run run) {
        RunStateAndResult<ThreadLocalWhenInTestAndSerializableEvents> result = currentState.processNewTestTask(newWrapper, threadLocalForParallelize, run);
        currentState = result.runState();
        return result.result();
    }

    @Override
    public void startAtomicOperation(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        currentState = currentState.startAtomicOperation(threadLocalDataWhenInTest);
    }

    @Override
    public void startAtomicOperationWithNewThread(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,
                                                  RunnableOrThreadWrapper newThread) {
        currentState = currentState.startAtomicOperationWithNewThread(threadLocalDataWhenInTest,
                newThread, runContext);
    }

    @Override
    public RuntimeEvent endAtomicOperation(RuntimeEvent runtimeEvent, ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        RunStateAndResult<RuntimeEvent> result = currentState.endAtomicOperation(this,
                runtimeEvent, threadLocalDataWhenInTest);
        currentState = result.runState();
        return result.result();
    }

    @Override
    public ActualRun end(ThreadLocalForParallelize threadLocalForParallelize) {
        threadLocalForParallelize.setParallelizedThreadLocalToNull();
        currentState = new RunStateEnd();
        return actualRun;
    }

    @Override
    public RuntimeEvent callAfterFromState(RuntimeEvent runtimeEvent,
                                           ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        return runtimeEvent.after(actualRun,runContext);
    }

    @Override
    public boolean canStartAtomicOperation() {
        return currentState.isStartAtomicOperationPossible();
    }

    @Override
    public void checkStopWaiting() throws TestBlockedException {
        currentState.checkStopWaiting(runContext);
    }
}
