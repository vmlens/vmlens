package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public interface ProcessRuntimeEventCallback {

    RuntimeEvent callAfterFromState(RuntimeEvent runtimeEvent,
                                    ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest);

}
