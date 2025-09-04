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
    public String operation() {
        return lockType.text()  +  " "  + monitorOperation.text() ;
    }

    @Override
    public String element(DescriptionContext context) {
        return "" + objectHashCode;
    }

    @Override
    public void addToNeedsDescription(NeedsDescriptionCallback callback) {

    }
}
