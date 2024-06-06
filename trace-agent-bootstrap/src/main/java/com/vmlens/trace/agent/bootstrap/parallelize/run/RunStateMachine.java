package com.vmlens.trace.agent.bootstrap.parallelize.run;


import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public interface RunStateMachine {
    boolean isActive(ThreadLocalDataWhenInTest threadLocalDataWhenInTest);

    void after(ParallelizeAction action, ThreadLocalDataWhenInTest threadLocalDataWhenInTest);

    ThreadLocalDataWhenInTest processNewTestTask(RunnableOrThreadWrapper newWrapper,
                                                 ThreadLocalForParallelize threadLocalForParallelize,
                                                 Run run);

    void setStateRecording();

    ActualRun end(ThreadLocalForParallelize threadLocalForParallelize);

}
