package com.vmlens.trace.agent.bootstrap.eventtype;

import gnu.trove.map.hash.THashMap;
import gnu.trove.map.hash.TIntObjectHashMap;

public class BarrierTypeCollection {

    public static final BarrierTypeCollection SINGLETON = new BarrierTypeCollectionFactory().create();

    private final THashMap<BarrierType,Integer> methodTypeToId;
    private final TIntObjectHashMap<BarrierType> idToMethodType;


    BarrierTypeCollection(THashMap<BarrierType, Integer> methodTypeToId,
                          TIntObjectHashMap<BarrierType> idToMethodType) {

        this.methodTypeToId = methodTypeToId;
        this.idToMethodType = idToMethodType;
    }


    public int toId(BarrierType barrierType) {
        if(! methodTypeToId.containsKey(barrierType)) {
            throw new RuntimeException("unknown " + barrierType.getClass());
        }
        return methodTypeToId.get(barrierType);
    }

    public BarrierType fromId(int id)  {
        if(! idToMethodType.containsKey(id)) {
            throw new RuntimeException("unknown " + id);
        }

        return idToMethodType.get(id);
    }

}
