package com.vmlens.trace.agent.bootstrap.barrierkeytype;

import gnu.trove.map.hash.THashMap;
import gnu.trove.map.hash.TIntObjectHashMap;

public class BarrierKeyTypeCollection {

    public static final BarrierKeyTypeCollection SINGLETON = new BarrierKeyTypeCollectionFactory().create();

    private final THashMap<BarrierKeyType,Integer> methodTypeToId;
    private final TIntObjectHashMap<BarrierKeyType> idToMethodType;


    BarrierKeyTypeCollection(THashMap<BarrierKeyType, Integer> methodTypeToId,
                          TIntObjectHashMap<BarrierKeyType> idToMethodType) {

        this.methodTypeToId = methodTypeToId;
        this.idToMethodType = idToMethodType;
    }


    public int toId(BarrierKeyType barrierType) {
        if(! methodTypeToId.containsKey(barrierType)) {
            throw new RuntimeException("unknown " + barrierType.getClass());
        }
        return methodTypeToId.get(barrierType);
    }

    public BarrierKeyType fromId(int id)  {
        if(! idToMethodType.containsKey(id)) {
            throw new RuntimeException("unknown " + id);
        }

        return idToMethodType.get(id);
    }

}
