package com.vmlens.expected.check;

import com.vmlens.report.assertion.EventForAssertionTypeVisitor;
import com.vmlens.report.assertion.TypeLockEnter;
import com.vmlens.report.assertion.TypeLockExit;
import com.vmlens.report.assertion.TypeVolatileFieldAccess;

public class IsEnterEvent implements EventForAssertionTypeVisitor {

    private boolean isEnterEvent;

    @Override
    public void visit(TypeLockEnter typeLockEnter) {
        isEnterEvent = true;
    }

    @Override
    public void visit(TypeLockExit typeLockExit) {
        isEnterEvent = false;
    }

    @Override
    public void visit(TypeVolatileFieldAccess typeVolatileFieldAccess) {
        throw new RuntimeException("should not be called");
    }


    public boolean isEnterEvent() {
        return isEnterEvent;
    }
}
