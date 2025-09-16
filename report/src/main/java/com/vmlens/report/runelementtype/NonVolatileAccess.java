package com.vmlens.report.runelementtype;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;
import com.vmlens.report.runelementtype.memoryaccesskey.MemoryAccessKey;
import com.vmlens.trace.agent.bootstrap.MemoryAccessType;

public class NonVolatileAccess implements RunElementType  {

    private final MemoryAccessKey memoryAccessKey;
    private final int operation;
    private final boolean isDataRace;

    public NonVolatileAccess(MemoryAccessKey memoryAccessKey, int operation, boolean isDataRace) {
        this.memoryAccessKey = memoryAccessKey;
        this.operation = operation;
        this.isDataRace = isDataRace;
    }

    @Override
    public String operation() {
        return modifier() + MemoryAccessType.asString(operation);
    }

    @Override
    public String element(DescriptionContext context) {
        return  memoryAccessKey.asString(context);
    }

    @Override
    public void addToNeedsDescription(NeedsDescriptionCallback callback) {
        memoryAccessKey.addToNeedsDescription(callback);
    }

    private String modifier() {
        if(isDataRace) {
            return "<span style=\"color: red;\">Data Race</span> ";
        }
        return "";
    }

    @Override
    public String object(DescriptionContext context) {
        return memoryAccessKey.objectHashCode();
    }
}
