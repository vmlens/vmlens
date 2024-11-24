package com.vmlens.trace.agent.bootstrap.callbackdeprecated.field;


import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;

public class FieldAccessVolatile implements FieldAccess {
    private final int operation;
    private final GetOrCreateObjectState getAndUpdateVolatileObjectState;
    private final ThreadLocalWhenInTestAdapter parallelizeBridgeForCallback;

    public FieldAccessVolatile(int operation,
                               GetOrCreateObjectState getAndUpdateVolatileObjectState,
                               ThreadLocalWhenInTestAdapter parallelizeBridgeForCallback) {
        this.operation = operation;
        this.getAndUpdateVolatileObjectState = getAndUpdateVolatileObjectState;
        this.parallelizeBridgeForCallback = parallelizeBridgeForCallback;
    }

    @Override
    public Long field_access_from_generated_method(Object orig, Long offset, int fieldId, int methodId) {

        return offset;
    }
}
