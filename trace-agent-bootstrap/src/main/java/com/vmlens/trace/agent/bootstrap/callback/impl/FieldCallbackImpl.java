package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionEndAtomicOperation;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl.ObjectHashCodeAndFieldId;
import com.vmlens.trace.agent.bootstrap.fieldidtostrategy.FieldRepositoryForCallback;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;

public class FieldCallbackImpl {

    private final FieldRepositoryForCallback fieldIdToStrategy;
    private final OrderMap<ObjectHashCodeAndFieldId> volatileFieldOrder;
    private final OrderMap<Integer> staticVolatileFieldOrder;
    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;

    public FieldCallbackImpl(FieldRepositoryForCallback fieldIdToStrategy,
                             OrderMap<ObjectHashCodeAndFieldId> volatileFieldOrder,
                             OrderMap<Integer> staticVolatileFieldOrder,
                             ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        this.fieldIdToStrategy = fieldIdToStrategy;
        this.volatileFieldOrder = volatileFieldOrder;
        this.staticVolatileFieldOrder = staticVolatileFieldOrder;
        this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
    }

    public void beforeFieldRead(Object fromObject, int fieldId, int position, int inMethodId) {
        fieldIdToStrategy.get(fieldId).onAccess(fromObject, fieldId, position, inMethodId,
                MemoryAccessType.IS_READ,
                volatileFieldOrder,
                threadLocalWhenInTestAdapter);
    }

    public void beforeFieldWrite(Object fromObject, int fieldId, int position, int inMethodId) {
        fieldIdToStrategy.get(fieldId).onAccess(fromObject, fieldId, position, inMethodId,
                MemoryAccessType.IS_WRITE,
                volatileFieldOrder,
                threadLocalWhenInTestAdapter);
    }

    public void beforeStaticFieldRead(int fieldId, int position, int inMethodId) {
        fieldIdToStrategy.get(fieldId).onStaticAccess(fieldId, position, inMethodId,
                MemoryAccessType.IS_READ,
                staticVolatileFieldOrder,
                threadLocalWhenInTestAdapter);
    }

    public void beforeStaticFieldWrite(int fieldId, int position, int inMethodId) {
        fieldIdToStrategy.get(fieldId).onStaticAccess(fieldId, position, inMethodId,
                MemoryAccessType.IS_WRITE,
                staticVolatileFieldOrder,
                threadLocalWhenInTestAdapter);
    }

    public void afterFieldAccess() {
        threadLocalWhenInTestAdapter.process(new CallbackActionEndAtomicOperation());
    }
}