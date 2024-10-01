package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

public class RunMock implements Run {

    @Override
    public RuntimeEvent after(RuntimeEvent runtimeEvent, ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        return runtimeEvent;
    }

    @Override
    public void newTask(RunnableOrThreadWrapper newWrapper, ThreadLocalForParallelize threadLocalForParallelize) {

    }

    @Override
    public ActualRun end(ThreadLocalForParallelize threadLocalForParallelize) {
        return null;
    }

    @Override
    public int runId() {
        return 0;
    }
}
