package com.vmlens.trace.agent.bootstrap.parallelize.run;


import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public interface RunStateMachine {
    boolean isActive(ThreadLocalDataWhenInTest threadLocalDataWhenInTest);

    void after(ParallelizeAction action, ThreadLocalDataWhenInTest threadLocalDataWhenInTest);

    boolean isNewTestTask(RunnableOrThreadWrapper newWrapper);

    void processNewTestTask(ThreadLocalDataWhenInTest threadLocalDataWhenInTest);

    void setStateRecording();

    ActualRun end();

}
