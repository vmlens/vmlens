package com.anarsoft.trace.agent.preanalyzed.model;

import com.vmlens.shaded.gnu.trove.map.hash.THashMap;

public class ClassTypeCollection {

    private final THashMap<Integer, ClassType> idToClassType = new THashMap<>();
    private final THashMap<Integer, MethodType> idToMethodType = new THashMap<>();

    private final THashMap<ClassType, Integer> classTypeToId = new THashMap<>();
    private final THashMap<MethodType, Integer> methodTypeToId = new THashMap<>();

    private int maxClassTypeId = 0;
    private int maxMethodTypeId = 0;

    public ClassTypeCollection() {
    }

    public int id(ClassType classType) {
        return classTypeToId.get(classType);
    }

    public int id(MethodType methodType) {
        return methodTypeToId.get(methodType);
    }

    public ClassType classType(int id) {
        return idToClassType.get(id);
    }

    public MethodType methodType(int id) {
        return idToMethodType.get(id);
    }

    private void add(ClassType classType) {
        idToClassType.put(maxClassTypeId, classType);
        classTypeToId.put(classType, maxClassTypeId);
        maxClassTypeId++;
    }

    private void add(MethodType classType) {
        idToMethodType.put(maxMethodTypeId, classType);
        methodTypeToId.put(classType, maxMethodTypeId);
        maxMethodTypeId++;
    }

}
