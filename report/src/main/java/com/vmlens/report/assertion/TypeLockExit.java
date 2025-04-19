package com.vmlens.report.assertion;

public class TypeLockExit implements EventForAssertionType {
    @Override
    public void accept(EventForAssertionTypeVisitor visitor) {
        visitor.visit(this);
    }

    @Override
    public String toString() {
        return "TypeLockExit{}";
    }
}
