package com.vmlens.trace.agent.bootstrap.parallelize.run;


import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalDataWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public interface RunStateMachine {
    boolean isActive(ThreadLocalDataWhenInTest threadLocalDataWhenInTest);

    RuntimeEvent after(RuntimeEvent runtimeEvent, ThreadLocalDataWhenInTest threadLocalDataWhenInTest);

    ThreadLocalDataWhenInTest processNewTestTask(RunnableOrThreadWrapper newWrapper,
                                                 ThreadLocalForParallelize threadLocalForParallelize,
                                                 Run run);
    void setStateRecording();

    ActualRun end(ThreadLocalForParallelize threadLocalForParallelize);

}
