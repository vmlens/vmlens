package com.vmlens.report.runelementtype;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;
import com.vmlens.report.runelementtype.memoryaccesskey.MemoryAccessKey;
import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import org.apache.commons.text.StringEscapeUtils;

public class VolatileAccess implements RunElementType {

    private final MemoryAccessKey memoryAccessKey;
    private final int operation;

    public VolatileAccess(MemoryAccessKey memoryAccessKey, int operation) {
        this.memoryAccessKey = memoryAccessKey;
        this.operation = operation;
    }

    @Override
    public String asString(DescriptionContext context) {
        return MemoryAccessType.asString(operation) +  " volatile " + memoryAccessKey.asString(context);
    }

    @Override
    public void addToNeedsDescription(NeedsDescriptionCallback callback) {
        memoryAccessKey.addToNeedsDescription(callback);
    }
}
