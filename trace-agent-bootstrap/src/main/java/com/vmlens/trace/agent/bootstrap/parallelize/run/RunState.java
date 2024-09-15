package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public interface RunState {
    boolean isActive(ThreadLocalDataWhenInTest threadLocalDataWhenInTest);

    RunStateAndRuntimeEvent after(RuntimeEvent runtimeEvent, ThreadLocalDataWhenInTest threadLocalDataWhenInTest);

    boolean isNewTestTask(RunnableOrThreadWrapper newWrapper);

    int getStartedThreadIndex();
}
