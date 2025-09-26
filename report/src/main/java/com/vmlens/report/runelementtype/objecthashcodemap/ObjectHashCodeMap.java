package com.vmlens.report.runelementtype.objecthashcodemap;

import java.util.HashMap;

public class ObjectHashCodeMap {

    private int maxCode;

    private final HashMap<Long,ThreadIndices> hashCodeToThreadIndices = new HashMap<>();

    public void add(long objectHashCode, int threadIndex) {
        ThreadIndices indices = hashCodeToThreadIndices.get(objectHashCode);
        if(indices == null) {
            hashCodeToThreadIndices.put(objectHashCode,new OneThreadIndex(threadIndex));
        } else  {
            hashCodeToThreadIndices.put(objectHashCode,indices.addThreadIndex(threadIndex,this));
        }
    }

    public String asUiString(long objectHashCode) {
        return hashCodeToThreadIndices.get(objectHashCode).asUiString();
    }

    public int newCode() {
         maxCode++;
        return maxCode;
    }
}
