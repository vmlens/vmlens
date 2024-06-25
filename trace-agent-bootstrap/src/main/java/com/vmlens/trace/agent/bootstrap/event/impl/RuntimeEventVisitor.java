package com.vmlens.trace.agent.bootstrap.event.impl;

public interface RuntimeEventVisitor {

    void visit(VolatileAccessEvent volatileAccessEvent);

    void visit(ThreadStartEvent threadStartEvent);

    void visit(ThreadJoinedEvent threadJoinedEvent);

}
