package com.vmlens.trace.agent.bootstrap.interleave.run;

import gnu.trove.map.hash.TLongIntHashMap;

public class NormalizeContext {

    private final TLongIntHashMap objectHashCodeToInt = new TLongIntHashMap();
    private int maxIndex;

    public int normalizeObjectHashCode(long objectHashCode) {
        if(objectHashCodeToInt.containsKey(objectHashCode)) {
            return objectHashCodeToInt.get(objectHashCode);
        }
        int temp = maxIndex;
        objectHashCodeToInt.put(objectHashCode,temp);
        maxIndex++;
        return temp;
    }

}
