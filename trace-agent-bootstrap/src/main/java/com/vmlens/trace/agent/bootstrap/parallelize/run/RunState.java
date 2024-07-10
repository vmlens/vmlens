package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public interface RunState {
    boolean isActive(ThreadLocalDataWhenInTest threadLocalDataWhenInTest);

    RunStateAndRuntimeEvent after(ParallelizeAction action, ThreadLocalDataWhenInTest threadLocalDataWhenInTest);

    boolean isNewTestTask(RunnableOrThreadWrapper newWrapper);

    int getStartedThreadIndex();
}
