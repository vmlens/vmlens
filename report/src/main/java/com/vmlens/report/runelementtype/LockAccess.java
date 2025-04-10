package com.vmlens.report.runelementtype;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;

public class LockAccess implements RunElementType {

    private final LockOperation monitorOperation;
    private final ReportLockType lockType;
    private final long objectHashCode;

    public LockAccess(LockOperation monitorOperation, ReportLockType lockType, long objectHashCode) {
        this.monitorOperation = monitorOperation;
        this.lockType = lockType;
        this.objectHashCode = objectHashCode;
    }

    @Override
    public String asString(DescriptionContext context) {
        return monitorOperation.text() + " " +  lockType.text() +  "@" + objectHashCode ;
    }

    @Override
    public void addToNeedsDescription(NeedsDescriptionCallback callback) {

    }
}
