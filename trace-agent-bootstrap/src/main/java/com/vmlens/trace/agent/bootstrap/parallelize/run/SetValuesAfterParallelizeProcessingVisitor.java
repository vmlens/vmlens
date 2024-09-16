package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callback.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEventVisitorWithDefault;
import com.vmlens.trace.agent.bootstrap.event.impl.ThreadStartEvent;

public class SetValuesAfterParallelizeProcessingVisitor extends RuntimeEventVisitorWithDefault {

    private final PerThreadCounter perThreadCounter;

    public SetValuesAfterParallelizeProcessingVisitor(PerThreadCounter perThreadCounter) {
        this.perThreadCounter = perThreadCounter;
    }

    @Override
    public void visit(ThreadStartEvent threadStartEvent) {
        threadStartEvent.setMethodCounter(perThreadCounter.methodCount());
        threadStartEvent.setStartedThreadToNull();
    }

    @Override
    protected void defaultMethod(RuntimeEvent runtimeEvent) {
        runtimeEvent.setMethodCounter(perThreadCounter.methodCount());
    }
}
