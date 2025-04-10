package com.vmlens.trace.agent.bootstrap.lock;

public interface LockTypeVisitor {
    
    void visitRead();
    void visitWrite();
    void visitReentrant();
    
    
}
