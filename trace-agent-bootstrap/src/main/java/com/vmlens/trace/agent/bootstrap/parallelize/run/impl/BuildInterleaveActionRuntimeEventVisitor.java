package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.event.impl.*;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadStart;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.VolatileFieldAccess;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public class BuildInterleaveActionRuntimeEventVisitor implements RuntimeEventVisitor {

    private InterleaveAction interleaveAction;

    @Override
    public void visit(VolatileAccessEvent volatileAccessEvent) {
        interleaveAction = new VolatileFieldAccess(
                volatileAccessEvent.threadIndex(),
                volatileAccessEvent.fieldId(),
                volatileAccessEvent.operation());
    }

    @Override
    public void visit(ThreadStartEvent threadStartEvent) {
        interleaveAction = new ThreadStart(
                threadStartEvent.threadIndex(),
                threadStartEvent.startedThreadIndex());
    }

    @Override
    public void visit(ThreadJoinedEvent threadJoinedEvent) {
        // Fixme
    }

    @Override
    public void visit(MethodEnterEvent methodEnterEvent) {
        // nothing to do
    }

    @Override
    public void visit(MethodExitEvent methodExitEvent) {
        // nothing to do
    }

    // can be null for example for method enter events
    public InterleaveAction build() {
        return interleaveAction;
    }
}
