package com.anarsoft.trace.agent.preanalyzed.model;

import com.vmlens.shaded.gnu.trove.map.hash.THashMap;

public class TypeCollection<TYPE> {

    private final THashMap<Integer, TYPE> idToType = new THashMap<>();

    private final THashMap<TYPE, Integer> typeToId = new THashMap<>();


    private int maxClassTypeId = 0;
    private int maxMethodTypeId = 0;

    public TypeCollection() {
    }

    public int id(TYPE classType) {
        return typeToId.get(classType);
    }

    public TYPE type(int id) {
        return idToType.get(id);
    }

    public void add(TYPE classType) {
        idToType.put(maxClassTypeId, classType);
        typeToId.put(classType, maxClassTypeId);
        maxClassTypeId++;
    }
}
