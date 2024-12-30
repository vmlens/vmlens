package com.vmlens.trace.agent.bootstrap.event;

public interface RuntimeEventVisitor {
    void visit(RuntimeEventOnly runtimeEventOnly);

    void visit(InterleaveActionFactory interleaveActionFactory);
}
