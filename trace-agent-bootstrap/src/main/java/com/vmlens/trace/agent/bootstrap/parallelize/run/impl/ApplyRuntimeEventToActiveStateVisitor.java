package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEventVisitorWithDefault;
import com.vmlens.trace.agent.bootstrap.event.impl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateAndRuntimeEvent;

public class ApplyRuntimeEventToActiveStateVisitor extends RuntimeEventVisitorWithDefault {

    private final ApplyRuntimeEventToActiveStateVisitorContext context;
    private RunStateAndRuntimeEvent result;

    public ApplyRuntimeEventToActiveStateVisitor(ApplyRuntimeEventToActiveStateVisitorContext context) {
        this.context = context;
    }

    @Override
    public void visit(ThreadStartEvent threadStartEvent) {
        int startedIndex = context.getThreadIndexForNewTestThread();
        threadStartEvent.setStartedThreadIndex(startedIndex);

        result = new RunStateAndRuntimeEvent(context.threadStarted(threadStartEvent.startedThread(), startedIndex),
                threadStartEvent);
    }

    @Override
    protected void defaultMethod(RuntimeEvent runtimeEvent) {
        result = new RunStateAndRuntimeEvent(context.current(), runtimeEvent);
    }

    public RunStateAndRuntimeEvent result() {
        return result;
    }
}
