package com.vmlens.report.input.run;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;
import com.vmlens.report.input.run.objecthashcodemap.ObjectHashCodeMap;


public class LockAccess implements RunElementType {

    private final LockOperation monitorOperation;
    private final ReportLockType lockType;
    private final long objectHashCode;
    private ObjectHashCodeMap objectHashCodeMap;

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
        return "";
    }

    @Override
    public void addToNeedsDescription(NeedsDescriptionCallback callback) {

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
