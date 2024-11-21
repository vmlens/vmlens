package com.vmlens.trace.agent.bootstrap.callbackdeprecated.field;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;

public class FieldAccessCallbackImpl {
    private final FieldAccess[] strategyArray;

    public FieldAccessCallbackImpl(GetOrCreateObjectState getAndUpdateVolatileObjectState,
                                   ThreadLocalWhenInTestAdapter parallelizeBridgeForCallback) {
        strategyArray = new FieldAccess[4];
        strategyArray[0] = new FieldAccessNonVolatile(MemoryAccessType.IS_READ);
        strategyArray[1] = new FieldAccessNonVolatile(MemoryAccessType.IS_WRITE);
        strategyArray[2] = new FieldAccessVolatile(MemoryAccessType.IS_READ,
                getAndUpdateVolatileObjectState, parallelizeBridgeForCallback);
        strategyArray[3] = new FieldAccessVolatile(MemoryAccessType.IS_WRITE,
                getAndUpdateVolatileObjectState, parallelizeBridgeForCallback);

    }

    public void field_access_static(int fieldId, int methodId, int callbackId) {
        //strategyArray[callbackId].field_access_static(fieldId, methodId);
    }

    public Long field_access_from_generated_method(Object orig, Long offset, int
            fieldId, int methodId, int callbackId) {
        return strategyArray[callbackId].field_access_from_generated_method(orig, offset, fieldId, methodId);
    }

    public void field_access(Object orig, int fieldId, int methodId, int callbackId) {
        //strategyArray[callbackId].field_access_default(orig, fieldId, methodId);
    }
}
