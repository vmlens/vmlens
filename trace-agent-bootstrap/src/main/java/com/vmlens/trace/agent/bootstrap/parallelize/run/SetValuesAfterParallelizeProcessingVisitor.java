package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callbackdeprecated.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.MethodEnterEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.MethodExitEvent;
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
