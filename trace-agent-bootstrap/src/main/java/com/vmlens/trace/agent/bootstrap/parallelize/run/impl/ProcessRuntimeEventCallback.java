package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWhenInTestForParallelize;

public interface ProcessRuntimeEventCallback {

    RuntimeEvent callAfterFromState(RuntimeEvent runtimeEvent,
                                    ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest);

}
