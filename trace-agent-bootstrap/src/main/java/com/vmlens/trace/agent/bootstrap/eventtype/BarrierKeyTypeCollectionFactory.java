package com.vmlens.trace.agent.bootstrap.eventtype;

import gnu.trove.map.hash.THashMap;
import gnu.trove.map.hash.TIntObjectHashMap;

public class BarrierKeyTypeCollectionFactory {

    private final THashMap<BarrierKeyType,Integer> methodTypeToId = new THashMap<>();
    private final TIntObjectHashMap<BarrierKeyType> idToMethodType = new TIntObjectHashMap<>();

    private int index = 0;


    public BarrierKeyTypeCollection create() {

        add(BarrierKeyTypeFuture.SINGLETON);


        return new BarrierKeyTypeCollection(methodTypeToId,idToMethodType);
    }

    private void add(BarrierKeyType methodType) {
        int id = index;
        methodTypeToId.put(methodType,id);
        idToMethodType.put(id,methodType);
        index++;
    }

}
