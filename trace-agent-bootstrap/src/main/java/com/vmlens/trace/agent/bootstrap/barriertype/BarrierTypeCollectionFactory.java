package com.vmlens.trace.agent.bootstrap.barriertype;

import gnu.trove.map.hash.THashMap;
import gnu.trove.map.hash.TIntObjectHashMap;

public class BarrierTypeCollectionFactory {

    private final THashMap<BarrierType,Integer> methodTypeToId = new THashMap<>();
    private final TIntObjectHashMap<BarrierType> idToMethodType = new TIntObjectHashMap<>();

    private int index = 0;


    public BarrierTypeCollection create() {

        add(BarrierTypeNotify.SINGLETON);
        add(BarrierTypeWait.SINGLETON);
        add(BarrierTypeWaitNotify.SINGLETON);

        return new BarrierTypeCollection(methodTypeToId,idToMethodType);
    }

    private void add(BarrierType methodType) {
        int id = index;
        methodTypeToId.put(methodType,id);
        idToMethodType.put(id,methodType);
        index++;
    }


}
