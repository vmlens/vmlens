package com.vmlens.trace.agent.bootstrap.repository;

import gnu.trove.map.hash.THashMap;

public class MethodCallIdMap {

    private final THashMap<MethodCallId, Integer> methodCallIdToInt = new THashMap<>();
    private int maxIndex = 0;

    public int asInt(MethodCallId methodCallId) {

        if (methodCallIdToInt.contains(methodCallId)) {
            return methodCallIdToInt.get(methodCallId);
        }
        int temp = maxIndex;
        maxIndex++;
        methodCallIdToInt.put(methodCallId, temp);
        return temp;
    }


}
