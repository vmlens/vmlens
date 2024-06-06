package com.vmlens.trace.agent.bootstrap.callback.field;

import com.vmlens.trace.agent.bootstrap.event.impl.VolatileAccessEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.action.ParallelizeActionForRuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;

public class FieldAccessVolatile implements FieldAccess {

    private final int operation;
    private final GetOrCreateObjectState getOrCreateObjectState;

    public FieldAccessVolatile(int operation, GetOrCreateObjectState getOrCreateObjectState) {
        this.operation = operation;
        this.getOrCreateObjectState = getOrCreateObjectState;
    }

    @Override
    public void field_access_from_generated_method(Object orig, int fieldId, int methodId,
                                                   ThreadLocalDataWhenInTest dataWhenInTest) {

        ObjectState objectState = getOrCreateObjectState.getOrCreate(orig);
        objectState.getAndIncrementOrder(fieldId);

        VolatileAccessEvent volatileAccessEvent = new VolatileAccessEvent();

        dataWhenInTest.after(new ParallelizeActionForRuntimeEvent(volatileAccessEvent));
        // Fixme

    }
}
