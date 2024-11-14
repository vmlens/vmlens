package com.vmlens.trace.agent.bootstrap.callbackdeprecated.field;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.CallbackActionApplyRuntimeEventFactory;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ParallelizeBridgeForCallback;

public class FieldAccessVolatile implements FieldAccess {
    private final int operation;
    private final GetOrCreateObjectState getAndUpdateVolatileObjectState;
    private final ParallelizeBridgeForCallback parallelizeBridgeForCallback;

    public FieldAccessVolatile(int operation,
                               GetOrCreateObjectState getAndUpdateVolatileObjectState,
                               ParallelizeBridgeForCallback parallelizeBridgeForCallback) {
        this.operation = operation;
        this.getAndUpdateVolatileObjectState = getAndUpdateVolatileObjectState;
        this.parallelizeBridgeForCallback = parallelizeBridgeForCallback;
    }

    @Override
    public Long field_access_from_generated_method(Object orig, Long offset, int fieldId, int methodId) {
        parallelizeBridgeForCallback.process(new CallbackActionApplyRuntimeEventFactory(
                new RuntimeEventFactoryVolatileField(orig, fieldId,
                        methodId, operation, getAndUpdateVolatileObjectState)));
        return offset;
    }
}
