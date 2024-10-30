package com.vmlens.trace.agent.bootstrap.callbackdeprecated.field;

public class FieldAccessNonVolatile implements FieldAccess {

    private final int operation;

    public FieldAccessNonVolatile(int operation) {
        this.operation = operation;
    }

    @Override
    public Long field_access_from_generated_method(Object orig, Long offset, int fieldId, int methodId) {
        return 0L;
    }
}
