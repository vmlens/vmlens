package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.*;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.RuntimeEventAndWarnings.of;

public class RunTestAdapter implements Run {

    private final RunStateMachine runStateMachine;

    public RunTestAdapter(RunStateMachine runStateMachine) {
        this.runStateMachine = runStateMachine;
    }

    @Override
    public RuntimeEventAndWarnings after(RuntimeEvent runtimeEvent, ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return of(runStateMachine.after(runtimeEvent, threadLocalDataWhenInTest));
    }

    @Override
    public void newTask(RunnableOrThreadWrapper newWrapper, ThreadLocalForParallelize threadLocalForParallelize) {
        runStateMachine.processNewTestTask(newWrapper, threadLocalForParallelize, this);
    }

    @Override
    public ActualRun end(ThreadLocalForParallelize threadLocalForParallelize) {
        return runStateMachine.end(threadLocalForParallelize);
    }

    @Override
    public int runId() {
        return 0;
    }

    @Override
    public void startAtomicOperation(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {

    }

    @Override
    public void startAtomicOperationWithNewThread(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest, RunnableOrThreadWrapper newThread) {

    }
}
