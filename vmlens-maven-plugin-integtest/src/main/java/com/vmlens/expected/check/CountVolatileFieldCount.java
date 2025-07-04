package com.vmlens.expected.check;

import com.vmlens.report.assertion.EventForAssertionTypeVisitor;
import com.vmlens.report.assertion.TypeLockEnter;
import com.vmlens.report.assertion.TypeLockExit;
import com.vmlens.report.assertion.TypeVolatileFieldAccess;

public class CountVolatileFieldCount implements EventForAssertionTypeVisitor  {

    private int count;

    @Override
    public void visit(TypeLockEnter typeLockEnter) {
        throw new RuntimeException("should not be called");
    }

    @Override
    public void visit(TypeLockExit typeLockExit) {
        throw new RuntimeException("should not be called");
    }

    @Override
    public void visit(TypeVolatileFieldAccess typeVolatileFieldAccess) {
        count++;
    }

    public int count() {
        return count;
    }
}
