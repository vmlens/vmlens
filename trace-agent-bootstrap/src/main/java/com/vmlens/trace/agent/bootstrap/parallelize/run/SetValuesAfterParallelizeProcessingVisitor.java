package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callback.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.impl.*;

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
    public void visit(MethodEnterEvent methodEnterEvent) {
        methodEnterEvent.setMethodCounter(perThreadCounter.incrementAndGetMethodCount());
    }

    @Override
    public void visit(MethodExitEvent methodExitEvent) {
        methodExitEvent.setMethodCounter(perThreadCounter.incrementAndGetMethodCount());
    }

    @Override
    protected void defaultMethod(RuntimeEvent runtimeEvent) {
        runtimeEvent.setMethodCounter(perThreadCounter.methodCount());
    }
}
