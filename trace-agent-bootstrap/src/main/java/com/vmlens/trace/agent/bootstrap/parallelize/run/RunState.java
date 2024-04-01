package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public interface RunState {
    boolean isActive(ThreadLocalDataWhenInTest threadLocalDataWhenInTest);

    RunState after(ParallelizeAction action, ThreadLocalDataWhenInTest threadLocalDataWhenInTest);

    boolean isNewTestTask(RunnableOrThreadWrapper newWrapper);

    void addTaskStartedInterleaveAction(ThreadLocalDataWhenInTest threadLocalDataWhenInTest, ActualRun calculatedRun);
}
