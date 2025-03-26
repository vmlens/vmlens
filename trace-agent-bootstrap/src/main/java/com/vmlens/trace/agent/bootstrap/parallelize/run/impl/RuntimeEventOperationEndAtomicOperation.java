package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;

public class RuntimeEventOperationEndAtomicOperation extends RuntimeEventOperation {

    @Override
    protected RuntimeEvent operation(RunStateMachine runStateMachine, RuntimeEvent runtimeEvent, ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return runStateMachine.endAtomicOperation(runtimeEvent, threadLocalDataWhenInTest);
    }

}
