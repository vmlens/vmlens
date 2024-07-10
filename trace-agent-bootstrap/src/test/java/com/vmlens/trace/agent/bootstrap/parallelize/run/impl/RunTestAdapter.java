package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.*;

public class RunTestAdapter implements Run {

    private final RunStateMachine runStateMachine;

    public RunTestAdapter(RunStateMachine runStateMachine) {
        this.runStateMachine = runStateMachine;
    }

    @Override
    public RuntimeEvent after(ParallelizeAction action, ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        return runStateMachine.after(action, threadLocalDataWhenInTest);
    }

    @Override
    public void newTask(RunnableOrThreadWrapper newWrapper, ThreadLocalForParallelize threadLocalForParallelize) {
        runStateMachine.processNewTestTask(newWrapper, threadLocalForParallelize, this);
    }

    @Override
    public ActualRun end(ThreadLocalForParallelize threadLocalForParallelize) {
        return runStateMachine.end(threadLocalForParallelize);
    }
}
