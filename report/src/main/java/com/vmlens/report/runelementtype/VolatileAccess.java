package com.vmlens.report.runelementtype;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;
import com.vmlens.report.runelementtype.memoryaccesskey.MemoryAccessKey;
import com.vmlens.trace.agent.bootstrap.MemoryAccessType;

public class VolatileAccess implements RunElementType {

    private final MemoryAccessKey memoryAccessKey;
    private final int operation;

    public VolatileAccess(MemoryAccessKey memoryAccessKey, int operation) {
        this.memoryAccessKey = memoryAccessKey;
        this.operation = operation;
    }

    @Override
    public String operation() {
        return "Volatile " +  MemoryAccessType.asString(operation);
    }

    @Override
    public String element(DescriptionContext context) {
        return   memoryAccessKey.asString(context);
    }

    @Override
    public void addToNeedsDescription(NeedsDescriptionCallback callback) {
        memoryAccessKey.addToNeedsDescription(callback);
    }

    @Override
    public String object(DescriptionContext context) {
        return  memoryAccessKey.objectHashCode();
    }
}
