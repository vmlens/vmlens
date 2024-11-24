package com.vmlens.trace.agent.bootstrap.event;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

public class RunStateMachineMock implements RunStateMachine {
    @Override
    public boolean isActive(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return false;
    }

    @Override
    public RuntimeEvent after(RuntimeEvent runtimeEvent, ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return runtimeEvent;
    }

    @Override
    public ThreadLocalWhenInTest processNewTestTask(RunnableOrThreadWrapper newWrapper, ThreadLocalForParallelize threadLocalForParallelize, Run run) {
        return null;
    }

    @Override
    public void setStateRecording() {

    }

    @Override
    public ActualRun end(ThreadLocalForParallelize threadLocalForParallelize) {
        return null;
    }
}
