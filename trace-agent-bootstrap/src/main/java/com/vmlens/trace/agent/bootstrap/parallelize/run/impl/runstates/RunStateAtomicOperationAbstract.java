package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstates;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestBlockedException;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ProcessRuntimeEventCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateAndResult;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;

public abstract class RunStateAtomicOperationAbstract implements RunState {

    protected final int threadIndexOfAtomicOperation;

    public RunStateAtomicOperationAbstract(int threadIndexOfAtomicOperation) {
        this.threadIndexOfAtomicOperation = threadIndexOfAtomicOperation;
    }

    @Override
    public boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        return threadLocalDataWhenInTest.threadIndex() == threadIndexOfAtomicOperation;
    }

    @Override
    public RunState startAtomicOperation(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        throw new IllegalStateException("should not be called");
    }

    @Override
    public RunStateAndResult<RuntimeEvent> after(ProcessRuntimeEventCallback processRuntimeEventCallback,
                                                 RuntimeEvent runtimeEvent,
                                                 ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        return RunStateAndResult.of(this,
                processRuntimeEventCallback.callAfterFromState(runtimeEvent, threadLocalDataWhenInTest));
    }

    @Override
    public RunState startAtomicOperationWithNewThread(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,
                                                      RunnableOrThreadWrapper newThread, ThreadIndexAndThreadStateMap runContext) {
        throw new IllegalStateException("should not be called");
    }

    @Override
    public boolean isStartAtomicOperationPossible() {
        return false;
    }

    @Override
    public void checkStopWaiting(ThreadIndexAndThreadStateMap runContext) throws TestBlockedException {

    }
}
