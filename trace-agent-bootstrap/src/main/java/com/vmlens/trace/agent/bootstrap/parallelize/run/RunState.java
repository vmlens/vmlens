package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public interface RunState {
    boolean isActive(ThreadLocalWhenInTest threadLocalDataWhenInTest);

    RunStateAndRuntimeEvent after(RuntimeEvent runtimeEvent, ThreadLocalWhenInTest threadLocalDataWhenInTest);

    boolean isNewTestTask(RunnableOrThreadWrapper newWrapper);

    int getStartedThreadIndex();
}
