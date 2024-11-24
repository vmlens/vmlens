package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RuntimeEventAndWarnings;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.RuntimeEventAndWarnings.withRuntimeEvent;

public class RunTestAdapter implements Run {

    private final RunStateMachine runStateMachine;

    public RunTestAdapter(RunStateMachine runStateMachine) {
        this.runStateMachine = runStateMachine;
    }

    @Override
    public RuntimeEventAndWarnings after(RuntimeEvent runtimeEvent, ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return withRuntimeEvent(runStateMachine.after(runtimeEvent, threadLocalDataWhenInTest));
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
}
