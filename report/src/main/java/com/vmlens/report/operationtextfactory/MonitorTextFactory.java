package com.vmlens.report.operationtextfactory;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;

public class MonitorTextFactory implements OperationTextFactory {

    private final MonitorOperation monitorOperation;
    private final long objectHashCode;

    public MonitorTextFactory(MonitorOperation monitorOperation, long objectHashCode) {
        this.monitorOperation = monitorOperation;
        this.objectHashCode = objectHashCode;
    }

    @Override
    public String create(DescriptionContext context) {
        return monitorOperation.text() + " " + objectHashCode;
    }

    @Override
    public void addToNeedsDescription(NeedsDescriptionCallback callback) {

    }
}
