package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstates;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWhenInTestForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ProcessRuntimeEventCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateAndResult;

public class RunStateAtomicOperationAfterNewThreadBegun extends RunStateAtomicOperationAbstract {

    private final int newThreadIndex;
    private final RunStateActive nextState;

    public RunStateAtomicOperationAfterNewThreadBegun(int threadIndexOfAtomicOperation, int newThreadIndex,
                                                      RunStateActive nextState) {
        super(threadIndexOfAtomicOperation);
        this.newThreadIndex = newThreadIndex;
        this.nextState = nextState;
    }

    @Override
    public RunStateAndResult<ThreadLocalWhenInTest> processNewTestTask(RunnableOrThreadWrapper newWrapper,
                                                                       ThreadLocalForParallelize threadLocalForParallelize,
                                                                       Run run) {
        return RunStateAndResult.of(this);
    }

    @Override
    public RunStateAndResult<RuntimeEvent> endAtomicOperation(ProcessRuntimeEventCallback processRuntimeEventCallback,
                                                              RuntimeEvent runtimeEvent,
                                                              ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        ThreadStartEvent threadStartEvent = (ThreadStartEvent) runtimeEvent;
        threadStartEvent.setThreadIndex(newThreadIndex);
        return RunStateAndResult.of(nextState,
                processRuntimeEventCallback.callAfterFromState(runtimeEvent, threadLocalDataWhenInTest));
    }

}
