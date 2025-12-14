package com.vmlens.report.input.run;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;
import com.vmlens.report.input.run.memoryaccesskey.MemoryAccessKey;
import com.vmlens.report.input.run.objecthashcodemap.ObjectHashCodeMap;
import com.vmlens.trace.agent.bootstrap.MemoryAccessType;

public class VolatileAccess implements RunElementType {

    private final MemoryAccessKey memoryAccessKey;
    private final int operation;
    private ObjectHashCodeMap objectHashCodeMap;

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
        if(memoryAccessKey.objectHashCode() != null) {
            return objectHashCodeMap.asUiString(memoryAccessKey.objectHashCode());
        }
        return "static";
    }

    @Override
    public void setObjectHashCodeMap(ObjectHashCodeMap objectHashCodeMap, int threadIndex) {
        if(memoryAccessKey.objectHashCode() != null) {
            this.objectHashCodeMap = objectHashCodeMap;
            objectHashCodeMap.add(memoryAccessKey.objectHashCode() ,threadIndex);
        }
    }
}
