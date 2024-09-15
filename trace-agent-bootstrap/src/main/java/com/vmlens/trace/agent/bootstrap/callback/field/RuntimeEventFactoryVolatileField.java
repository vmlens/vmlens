package com.vmlens.trace.agent.bootstrap.callback.field;

import com.vmlens.trace.agent.bootstrap.callback.state.ObjectIdAndOrder;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.impl.RuntimeEventFactory;
import com.vmlens.trace.agent.bootstrap.event.impl.VolatileAccessEvent;

public class RuntimeEventFactoryVolatileField implements RuntimeEventFactory {

    private final Object orig;
    private final int fieldId;
    private final int methodId;
    private final int operation;
    private final GetOrCreateObjectState getAndUpdateVolatileObjectState;

    public RuntimeEventFactoryVolatileField(Object orig, int fieldId,
                                            int methodId, int operation,
                                            GetOrCreateObjectState getAndUpdateVolatileObjectState) {
        this.orig = orig;
        this.fieldId = fieldId;
        this.methodId = methodId;
        this.operation = operation;
        this.getAndUpdateVolatileObjectState = getAndUpdateVolatileObjectState;
    }

    @Override
    public VolatileAccessEvent create() {
        ObjectState state = getAndUpdateVolatileObjectState.getOrCreate(orig);
        ObjectIdAndOrder objectIdAndOrder = state.getAndIncrementOrder(fieldId);
        VolatileAccessEvent volatileAccessEvent = new VolatileAccessEvent();
        volatileAccessEvent.setFieldId(fieldId);
        volatileAccessEvent.setMethodId(methodId);
        volatileAccessEvent.setObjectHashCode(objectIdAndOrder.id);
        volatileAccessEvent.setOrder(objectIdAndOrder.order);
        volatileAccessEvent.setOperation(operation);
        return volatileAccessEvent;
    }
}
