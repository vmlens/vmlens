package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEventVisitor;
import com.vmlens.trace.agent.bootstrap.event.impl.ThreadJoinedEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.ThreadStartEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.VolatileAccessEvent;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.VolatileFieldAccess;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public class InterleaveActionBuilder implements RuntimeEventVisitor {

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
        // Fixme
    }

    @Override
    public void visit(ThreadJoinedEvent threadJoinedEvent) {
        // Fixme
    }

    public InterleaveAction build() {
        return interleaveAction;
    }
}
