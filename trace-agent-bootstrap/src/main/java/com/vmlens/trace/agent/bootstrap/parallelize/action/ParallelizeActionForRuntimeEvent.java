package com.vmlens.trace.agent.bootstrap.parallelize.action;

import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ActionContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateAndRuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;

public class ParallelizeActionForRuntimeEvent implements ParallelizeAction {
    private final RuntimeEvent runtimeEvent;

    public ParallelizeActionForRuntimeEvent(RuntimeEvent runtimeEvent) {
        this.runtimeEvent = runtimeEvent;
    }

    @Override
    public RunStateAndRuntimeEvent execute(ActionContext context) {
        return new RunStateAndRuntimeEvent(context.current(), runtimeEvent);
    }
}
