package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;

public class RunStateAndRuntimeEvent {

    private final RunState runState;
    private final RuntimeEvent runtimeEvent;

    public RunStateAndRuntimeEvent(RunState runState, RuntimeEvent runtimeEvent) {
        this.runState = runState;
        this.runtimeEvent = runtimeEvent;
    }

    public RunState runState() {
        return runState;
    }

    public RuntimeEvent runtimeEvent() {
        return runtimeEvent;
    }
}
