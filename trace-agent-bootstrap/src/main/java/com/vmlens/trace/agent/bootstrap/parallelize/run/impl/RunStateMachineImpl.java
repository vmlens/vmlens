package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveInfo;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWhenInTestForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstates.RunStateEnd;

public class RunStateMachineImpl implements RunStateMachine, ProcessRuntimeEventCallback {

    private final ThreadLocalDataWhenInTestMap runContext;
    private final ActualRun actualRun;
    private RunState currentState;

    public RunStateMachineImpl(ActualRun actualRun,
                               ThreadLocalDataWhenInTestMap runContext,
                               RunState initialState) {
        this.actualRun = actualRun;
        this.currentState = initialState;
        this.runContext = runContext;
    }

    @Override
    public boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        return currentState.isActive(threadLocalDataWhenInTest);
    }

    @Override
    public RuntimeEvent after(RuntimeEvent runtimeEvent, ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        RunStateAndResult<RuntimeEvent> result = currentState.after(this, runtimeEvent, threadLocalDataWhenInTest);
        currentState = result.runState();
        return result.result();
    }

    @Override
    public ThreadLocalWhenInTest processNewTestTask(RunnableOrThreadWrapper newWrapper,
                                                    ThreadLocalForParallelize threadLocalForParallelize, Run run) {
        RunStateAndResult<ThreadLocalWhenInTest> result = currentState.processNewTestTask(newWrapper, threadLocalForParallelize, run);
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
        if (!(runtimeEvent instanceof InterleaveActionFactory)) {
            return runtimeEvent;
        }
        InterleaveActionFactory interleaveActionFactory = (InterleaveActionFactory) runtimeEvent;
        InterleaveInfo interleaveInfo = actualRun.after(interleaveActionFactory.create());
        if (interleaveInfo != null) {
            runtimeEvent.setRunPosition(interleaveInfo.runPosition());
            return runtimeEvent;
        }
        return null;
    }

    @Override
    public boolean isStartAtomicOperationPossible() {
        return currentState.isStartAtomicOperationPossible();
    }
}
