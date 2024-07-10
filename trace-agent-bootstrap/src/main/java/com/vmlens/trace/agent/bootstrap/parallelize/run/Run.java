package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;


public interface Run {
    RuntimeEvent after(ParallelizeAction action, ThreadLocalDataWhenInTest threadLocalDataWhenInTest);

    void newTask(RunnableOrThreadWrapper newWrapper, ThreadLocalForParallelize threadLocalForParallelize);

    ActualRun end(ThreadLocalForParallelize threadLocalForParallelize);
}
