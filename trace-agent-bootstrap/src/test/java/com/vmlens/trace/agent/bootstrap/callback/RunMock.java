package com.vmlens.trace.agent.bootstrap.callback;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RuntimeEventAndWarnings;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.RuntimeEventAndWarnings.withRuntimeEvent;

public class RunMock implements Run {
    @Override
    public int runId() {
        return 0;
    }

    @Override
    public RuntimeEventAndWarnings after(RuntimeEvent runtimeEvent, ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return withRuntimeEvent(runtimeEvent);
    }

    @Override
    public void newTask(RunnableOrThreadWrapper newWrapper, ThreadLocalForParallelize threadLocalForParallelize) {

    }

    @Override
    public ActualRun end(ThreadLocalForParallelize threadLocalForParallelize) {
        return null;
    }
}
