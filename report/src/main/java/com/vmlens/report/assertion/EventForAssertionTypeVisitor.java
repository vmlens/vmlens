package com.vmlens.report.assertion;

public interface EventForAssertionTypeVisitor {
    void visit(TypeLockEnter typeLockEnter);
    void visit(TypeLockExit typeLockExit);
    void visit(TypeVolatileFieldAccess typeVolatileFieldAccess);
}
