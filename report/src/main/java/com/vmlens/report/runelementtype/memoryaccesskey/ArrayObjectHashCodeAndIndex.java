package com.vmlens.report.runelementtype.memoryaccesskey;

import com.vmlens.report.description.DescriptionContext;
import com.vmlens.report.description.NeedsDescriptionCallback;

public class ArrayObjectHashCodeAndIndex implements MemoryAccessKey  {

    private final long objectHashCode;
    private final int arrayIndex;

    public ArrayObjectHashCodeAndIndex(long objectHashCode, int arrayIndex) {
        this.objectHashCode = objectHashCode;
        this.arrayIndex = arrayIndex;
    }

    @Override
    public String asString(DescriptionContext context) {
        return " array[" + arrayIndex  + "]@" + objectHashCode;
    }

    @Override
    public void addToNeedsDescription(NeedsDescriptionCallback callback) {
   
    }
    
}
