package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstates;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWhenInTestForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ProcessRuntimeEventCallback;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateAndResult;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadLocalDataWhenInTestMap;

public class RunStateActive implements RunState {


    private final ActiveStrategy activeStrategy;

    public RunStateActive(ActiveStrategy activeStrategy) {
        this.activeStrategy = activeStrategy;
    }

    public static RunState createInterleaved(CalculatedRun calculatedRun) {
        return new RunStateActive(new ActiveStrategyInterleaved(calculatedRun));
    }

    public static RunState createRecording() {
        return new RunStateActive(new ActiveStrategyRecording());
    }


    @Override
    public boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        return activeStrategy.isActive(threadLocalDataWhenInTest);
    }

    @Override
    public RunStateAndResult<ThreadLocalWhenInTest> processNewTestTask(RunnableOrThreadWrapper newWrapper, ThreadLocalForParallelize threadLocalForParallelize, Run run) {
        return RunStateAndResult.of(this);
    }

    @Override
    public RunState startAtomicOperation(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        return new RunStateAtomicOperation(threadLocalDataWhenInTest.threadIndex(), this);
    }

    @Override
    public RunState startAtomicOperationWithNewThread(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,
                                                      RunnableOrThreadWrapper newThread,
                                                      ThreadLocalDataWhenInTestMap runContext) {
        return new RunStateAtomicOperationWithNewThreadStarted(threadLocalDataWhenInTest.threadIndex(), newThread,
                runContext, this);
    }

    @Override
    public RunStateAndResult<RuntimeEvent> endAtomicOperation(ProcessRuntimeEventCallback processRuntimeEventCallback,
                                                              RuntimeEvent runtimeEvent,
                                                              ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
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
    public boolean isStartAtomicOperationPossible() {
        return true;
    }
}
