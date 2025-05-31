package com.vmlens.trace.agent.bootstrap.description;

public interface ThreadOrLoopDescriptionVisitor {
    void visit(ThreadDescription threadDescription);

    void visit(TestLoopDescription whileLoopDescription);
}
