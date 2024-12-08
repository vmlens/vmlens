package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstates;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWhenInTestForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ProcessRuntimeEventCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateAndResult;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadLocalDataWhenInTestMap;


public class RunStateNewThreadStarted implements RunState {

    private final RunnableOrThreadWrapper startedThread;
    private final ThreadLocalDataWhenInTestMap runContext;
    private final int startedThreadIndex;
    private final RunStateActive nextState;

    public RunStateNewThreadStarted(RunnableOrThreadWrapper startedThread,
                                    ThreadLocalDataWhenInTestMap runContext,
                                    int startedThreadIndex,
                                    RunStateActive nextState) {
        this.startedThread = startedThread;
        this.runContext = runContext;
        this.startedThreadIndex = startedThreadIndex;
        this.nextState = nextState;
    }

    @Override
    public boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        return false;
    }

    @Override
    public RunStateAndResult<ThreadLocalWhenInTest> processNewTestTask(RunnableOrThreadWrapper newWrapper, ThreadLocalForParallelize threadLocalForParallelize, Run run) {
        if (!startedThread.equals(newWrapper)) {
            return RunStateAndResult.of(this);
        }
        ThreadLocalWhenInTest threadLocalDataWhenInTest = runContext.createForStartedThread(
                run, threadLocalForParallelize.threadId(), startedThreadIndex);
        threadLocalForParallelize.setThreadLocalDataWhenInTest(threadLocalDataWhenInTest);

        return RunStateAndResult.of(nextState, threadLocalDataWhenInTest);
    }

    @Override
    public RunState startAtomicOperation(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        throw new IllegalStateException("should not be called");
    }

    @Override
    public RunState startAtomicOperationWithNewThread(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,
                                                      RunnableOrThreadWrapper newThread,
                                                      ThreadLocalDataWhenInTestMap runContext) {
        throw new IllegalStateException("should not be called");
    }

    @Override
    public RunStateAndResult<RuntimeEvent> endAtomicOperation(ProcessRuntimeEventCallback processRuntimeEventCallback,
                                                              RuntimeEvent runtimeEvent,
                                                              ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        throw new IllegalStateException("should not be called");
    }

    @Override
    public RunStateAndResult<RuntimeEvent> after(ProcessRuntimeEventCallback processRuntimeEventCallback, RuntimeEvent runtimeEvent, ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        return RunStateAndResult.of(this,
                processRuntimeEventCallback.callAfterFromState(runtimeEvent, threadLocalDataWhenInTest));
    }

    @Override
    public boolean isStartAtomicOperationPossible() {
        return false;
    }
}
