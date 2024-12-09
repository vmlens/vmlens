package com.vmlens.trace.agent.bootstrap.ordermap;

import gnu.trove.map.hash.THashMap;

public class OrderMap<KEY> {

    private final THashMap<KEY, Integer> keyToOrder = new THashMap<>();

    public synchronized int getAndIncrementOrder(KEY key) {
        Integer currentOrder = keyToOrder.get(key);
        if (currentOrder == null) {
            keyToOrder.put(key, 1);
            return 0;
        }
        keyToOrder.put(key, currentOrder + 1);
        return currentOrder;
    }
}
