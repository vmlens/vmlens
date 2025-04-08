package com.vmlens.trace.agent.bootstrap.callback.impl;

import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.OnAfterMethodCall;
import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTestAdapter;
import com.vmlens.trace.agent.bootstrap.fieldrepository.FieldRepositoryForCallback;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class FieldCallbackImpl {

    private final FieldRepositoryForCallback fieldIdToStrategy;
    private final ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter;
    private final ReadWriteLockMap readWriteLockMap;

    public FieldCallbackImpl(FieldRepositoryForCallback fieldIdToStrategy,
                             ThreadLocalWhenInTestAdapter threadLocalWhenInTestAdapter,
                             ReadWriteLockMap readWriteLockMap) {
        this.fieldIdToStrategy = fieldIdToStrategy;
        this.threadLocalWhenInTestAdapter = threadLocalWhenInTestAdapter;
        this.readWriteLockMap = readWriteLockMap;
    }

    public void beforeFieldRead(Object fromObject, int fieldId, int position, int inMethodId) {
        fieldIdToStrategy.get(fieldId).onAccess(fromObject, fieldId, position, inMethodId,
                MemoryAccessType.IS_READ,
                threadLocalWhenInTestAdapter);
    }

    public void beforeFieldWrite(Object fromObject, int fieldId, int position, int inMethodId) {
        fieldIdToStrategy.get(fieldId).onAccess(fromObject, fieldId, position, inMethodId,
                MemoryAccessType.IS_WRITE,
                threadLocalWhenInTestAdapter);
    }

    public void beforeStaticFieldRead(int fieldId, int position, int inMethodId) {
        fieldIdToStrategy.get(fieldId).onStaticAccess(fieldId, position, inMethodId,
                MemoryAccessType.IS_READ,
                threadLocalWhenInTestAdapter);
    }

    public void beforeStaticFieldWrite(int fieldId, int position, int inMethodId) {
        fieldIdToStrategy.get(fieldId).onStaticAccess(fieldId, position, inMethodId,
                MemoryAccessType.IS_WRITE,
                threadLocalWhenInTestAdapter);
    }

    public void afterFieldAccess() {
        threadLocalWhenInTestAdapter.process(new OnAfterMethodCall(-1, -1,readWriteLockMap));
    }
}