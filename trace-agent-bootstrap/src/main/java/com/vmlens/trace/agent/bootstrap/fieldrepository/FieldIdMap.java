package com.vmlens.trace.agent.bootstrap.fieldrepository;

import gnu.trove.map.hash.THashMap;

public class FieldIdMap {

    private final THashMap<FieldId, Integer> fieldIdIdToInt = new THashMap<>();
    private int maxIndex = 0;

    public int asInt(FieldId fieldId) {

        if (fieldIdIdToInt.contains(fieldId)) {
            return fieldIdIdToInt.get(fieldId);
        }
        int temp = maxIndex;
        maxIndex++;
        fieldIdIdToInt.put(fieldId, temp);
        return temp;
    }

}
