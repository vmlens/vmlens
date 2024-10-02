package com.anarsoft.trace.agent.description;

public interface ThreadOrLoopDescriptionVisitor {
    void visit(ThreadDescription threadDescription);

    void visit(TestLoopDescription whileLoopDescription);
}
