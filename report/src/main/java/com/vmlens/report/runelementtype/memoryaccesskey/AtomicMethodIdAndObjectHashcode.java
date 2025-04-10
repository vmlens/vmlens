package com.vmlens.report.runelementtype.memoryaccesskey;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;

public class AtomicMethodIdAndObjectHashcode implements MemoryAccessKey  {

    private final int atomicMethodId;
    private final long objectHashCode;

    public AtomicMethodIdAndObjectHashcode(int atomicMethodId, long objectHashCode) {
        this.atomicMethodId = atomicMethodId;
        this.objectHashCode = objectHashCode;
    }

    @Override
    public String asString(DescriptionContext context) {
        return context.methodName(atomicMethodId) + "@" + objectHashCode;
    }

    @Override
    public void addToNeedsDescription(NeedsDescriptionCallback callback) {
        callback.needsMethod(atomicMethodId);
    }

}
