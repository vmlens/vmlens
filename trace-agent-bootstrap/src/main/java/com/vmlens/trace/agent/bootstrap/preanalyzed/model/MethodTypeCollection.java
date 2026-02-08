package com.vmlens.trace.agent.bootstrap.preanalyzed.model;

import com.vmlens.trace.agent.bootstrap.preanalyzed.model.methodtypeimpl.AbstractMethodType;
import gnu.trove.map.hash.THashMap;
import gnu.trove.map.hash.TIntObjectHashMap;


public class MethodTypeCollection {

    private final THashMap<AbstractMethodType,Integer> methodTypeToId;
    private final TIntObjectHashMap<AbstractMethodType> idToMethodType;

    MethodTypeCollection(THashMap<AbstractMethodType, Integer> methodTypeToId,
                                TIntObjectHashMap<AbstractMethodType> idToMethodType) {
        this.methodTypeToId = methodTypeToId;
        this.idToMethodType = idToMethodType;
    }


    public int id(MethodType methodType) {
        if(! methodTypeToId.containsKey(methodType)) {
            throw new RuntimeException("unknown " + methodType.getClass());
        }
        return methodTypeToId.get(methodType);

    }

    public MethodType type(int id) {
       if(! idToMethodType.containsKey(id)) {
           throw new RuntimeException("unknown " + id);
       }

        return idToMethodType.get(id);
    }

}
