package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callback.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEventVisitorWithDefault;

public class SetMethodCounterVisitor extends RuntimeEventVisitorWithDefault {

    private final PerThreadCounter perThreadCounter;

    public SetMethodCounterVisitor(PerThreadCounter perThreadCounter) {
        this.perThreadCounter = perThreadCounter;
    }

    @Override
    protected void defaultMethod(RuntimeEvent runtimeEvent) {
        runtimeEvent.setMethodCounter(perThreadCounter.methodCount());
    }
}
