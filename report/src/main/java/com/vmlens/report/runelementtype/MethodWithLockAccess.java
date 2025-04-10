package com.vmlens.report.runelementtype;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;

public class MethodWithLockAccess implements RunElementType {

    private final int atomicMethodId;
    private final long objectHashCode;
    private final LockOperation lockOperation;
    private final ReportLockType lockType;

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
    public String asString(DescriptionContext context) {
        return lockOperation.text() + " "  + lockType.textForMethodWithLock() + " " +  context.methodName(atomicMethodId) + "@" + objectHashCode;
    }

    @Override
    public void addToNeedsDescription(NeedsDescriptionCallback callback) {
        callback.needsMethod(atomicMethodId);
    }
}
