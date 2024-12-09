package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.callback.callbackaction.CallbackActionEndAtomicOperation;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.callbackdeprecated.field.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldIdToStrategy;
import com.vmlens.trace.agent.bootstrap.ordermap.OrderMap;

public class FieldCallbackImpl {

    private final FieldIdToStrategy fieldIdToStrategy;
    private final OrderMap volatileAccessRepository;
    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;

    public FieldCallbackImpl(FieldIdToStrategy fieldIdToStrategy,
                             OrderMap volatileAccessRepository,
                             ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter) {
        this.fieldIdToStrategy = fieldIdToStrategy;
        this.volatileAccessRepository = volatileAccessRepository;
        this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
    }

    public void beforeFieldRead(Object fromObject, int fieldId, int position, int inMethodId) {
        fieldIdToStrategy.get(fieldId).onAccess(fromObject, fieldId, position, inMethodId,
                MemoryAccessType.IS_READ,
                volatileAccessRepository,
                threadLocalWhenInTestAdapter);
    }

    public void beforeFieldWrite(Object fromObject, int fieldId, int position, int inMethodId) {
        fieldIdToStrategy.get(fieldId).onAccess(fromObject, fieldId, position, inMethodId,
                MemoryAccessType.IS_WRITE,
                volatileAccessRepository,
                threadLocalWhenInTestAdapter);
    }

    public void beforeStaticFieldRead(int fieldId, int position, int inMethodId) {
        fieldIdToStrategy.get(fieldId).onStaticAccess(fieldId, position, inMethodId,
                MemoryAccessType.IS_READ,
                volatileAccessRepository,
                threadLocalWhenInTestAdapter);
    }

    public void beforeStaticFieldWrite(int fieldId, int position, int inMethodId) {
        fieldIdToStrategy.get(fieldId).onStaticAccess(fieldId, position, inMethodId,
                MemoryAccessType.IS_WRITE,
                volatileAccessRepository,
                threadLocalWhenInTestAdapter);
    }

    public void afterFieldAccess() {
        threadLocalWhenInTestAdapter.process(new CallbackActionEndAtomicOperation());
    }
}