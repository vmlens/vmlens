package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.*;

public class RunStateActive implements RunState, ApplyRuntimeEventToActiveStateVisitorContext {

    private final ActualRun actualRun;
    private final ActiveStrategy activeStrategy;
    private final ThreadLocalDataWhenInTestMap runContext;
    private final CallAfterForRuntimeEvent callAfterForRuntimeEvent = new CallAfterForRuntimeEvent();

    public RunStateActive(ActualRun actualRun, ThreadLocalDataWhenInTestMap runContext,
                          ActiveStrategy activeStrategy) {
        this.actualRun = actualRun;
        this.runContext = runContext;
        this.activeStrategy = activeStrategy;
    }

    public static RunState createInterleaved(ActualRun actualRun, ThreadLocalDataWhenInTestMap runContext,
                                             CalculatedRun calculatedRun) {
        return new RunStateActive(actualRun, runContext, new ActiveStrategyInterleaved(calculatedRun));
    }

    public static RunState createRecording(ActualRun actualRun, ThreadLocalDataWhenInTestMap runContext) {
        return new RunStateActive(actualRun, runContext, new ActiveStrategyRecording());
    }

    @Override
    public RunStateAndRuntimeEvent after(RuntimeEvent runtimeEvent, ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        ApplyRuntimeEventToActiveStateVisitor visitor = new ApplyRuntimeEventToActiveStateVisitor(this);
        runtimeEvent.accept(visitor);
        RunStateAndRuntimeEvent result = visitor.result();

        return new RunStateAndRuntimeEvent(result.runState(),
                processRuntimeEvent(result.runtimeEvent()));
    }

    // for Test
    public RuntimeEvent processRuntimeEvent(RuntimeEvent runtimeEvent) {
        return callAfterForRuntimeEvent.after(runtimeEvent, actualRun);
    }

    @Override
    public boolean isNewTestTask(RunnableOrThreadWrapper newWrapper) {
        return false;
    }

    @Override
    public RunState current() {
        return this;
    }

    @Override
    public RunState threadStarted(RunnableOrThreadWrapper startedThread, int startedThreadIndex) {
        return new RunStateNewThreadStarted(startedThread, runContext, startedThreadIndex);
    }

    public boolean isActive(ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        return activeStrategy.isActive(threadLocalDataWhenInTest);
    }

    @Override
    public int getThreadIndexForNewTestThread() {
        return runContext.getThreadIndexForNewTestThread();
    }

    @Override
    public int getStartedThreadIndex() {
        throw new RuntimeException("should not be called");
    }
}
