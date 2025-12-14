package com.vmlens.report.input.run;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;
import com.vmlens.report.input.run.objecthashcodemap.ObjectHashCodeMap;


public class MethodWithLockAccess implements RunElementType {

    private final int atomicMethodId;
    private final long objectHashCode;
    private final LockOperation lockOperation;
    private final ReportLockType lockType;
    private ObjectHashCodeMap objectHashCodeMap;

    public MethodWithLockAccess(int atomicMethodId,
                                long objectHashCode, 
                                LockOperation lockOperation,
                                ReportLockType lockType) {
        this.atomicMethodId = atomicMethodId;
        this.objectHashCode = objectHashCode;
        this.lockOperation = lockOperation;
        this.lockType = lockType;
    }

    @Override
    public String operation() {
        return  lockType.textForMethodWithLock() + " " + lockOperation.text();
    }

    @Override
    public String element(DescriptionContext context) {
        return context.methodName(atomicMethodId);
    }

    @Override
    public void addToNeedsDescription(NeedsDescriptionCallback callback) {
        callback.needsMethod(atomicMethodId);
    }

    @Override
    public String object(DescriptionContext context) {
        return objectHashCodeMap.asUiString(objectHashCode);
    }

    @Override
    public void setObjectHashCodeMap(ObjectHashCodeMap objectHashCodeMap, int threadIndex) {
        this.objectHashCodeMap = objectHashCodeMap;
        objectHashCodeMap.add(objectHashCode,threadIndex);
    }
}
