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
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadLocalDataWhenInTestMap;


public class RunStateAtomicOperationWithNewThreadStarted extends RunStateAtomicOperationAbstract {

    private final RunnableOrThreadWrapper startedThread;
    private final ThreadLocalDataWhenInTestMap runContext;
    private final RunStateActive nextState;


    public RunStateAtomicOperationWithNewThreadStarted(int threadIndexOfAtomicOperation,
                                                       RunnableOrThreadWrapper startedThread,
                                                       ThreadLocalDataWhenInTestMap runContext,
                                                       RunStateActive nextState) {
        super(threadIndexOfAtomicOperation);
        this.startedThread = startedThread;
        this.runContext = runContext;
        this.nextState = nextState;
    }

    @Override
    public RunStateAndResult<ThreadLocalWhenInTest> processNewTestTask(RunnableOrThreadWrapper newWrapper,
                                                                       ThreadLocalForParallelize threadLocalForParallelize,
                                                                       Run run) {
        if (!startedThread.equals(newWrapper)) {
            return RunStateAndResult.of(this);
        }
        int newThreadIndex = runContext.getThreadIndexForNewTestThread();
        ThreadLocalWhenInTest threadLocalDataWhenInTest = runContext.createForStartedThread(
                run, threadLocalForParallelize.threadId(), newThreadIndex);
        threadLocalForParallelize.setThreadLocalDataWhenInTest(threadLocalDataWhenInTest);

        return RunStateAndResult.of(new RunStateAtomicOperationAfterNewThreadBegun(threadIndexOfAtomicOperation,
                        newThreadIndex,
                        nextState),
                threadLocalDataWhenInTest);
    }


    @Override
    public RunStateAndResult<RuntimeEvent> endAtomicOperation(ProcessRuntimeEventCallback processRuntimeEventCallback,
                                                              RuntimeEvent runtimeEvent,
                                                              ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        int newThreadIndex = runContext.getThreadIndexForNewTestThread();
        ThreadStartEvent threadStartEvent = (ThreadStartEvent) runtimeEvent;
        threadStartEvent.setStartedThreadIndex(newThreadIndex);

        return RunStateAndResult.of(new RunStateNewThreadStarted(startedThread, runContext, newThreadIndex, nextState),
                processRuntimeEventCallback.callAfterFromState(runtimeEvent, threadLocalDataWhenInTest));
    }
}
