package com.vmlens.trace.agent.bootstrap.parallelize.run;


import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public interface RunStateMachine {
    boolean isActive(ThreadLocalWhenInTest threadLocalDataWhenInTest);

    RuntimeEvent after(RuntimeEvent runtimeEvent, ThreadLocalWhenInTest threadLocalDataWhenInTest);

    ThreadLocalWhenInTest processNewTestTask(RunnableOrThreadWrapper newWrapper,
                                             ThreadLocalForParallelize threadLocalForParallelize,
                                             Run run);
    void setStateRecording();

    ActualRun end(ThreadLocalForParallelize threadLocalForParallelize);

}
