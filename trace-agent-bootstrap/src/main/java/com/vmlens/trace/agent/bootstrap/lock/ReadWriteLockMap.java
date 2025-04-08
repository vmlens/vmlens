package com.vmlens.trace.agent.bootstrap.lock;

import gnu.trove.map.hash.TLongLongHashMap;

public class ReadWriteLockMap {

    public static final ReadWriteLockMap INSTANCE = new ReadWriteLockMap();

    private final TLongLongHashMap dependentToUnderlying = new TLongLongHashMap();

    public synchronized long getUnderlying(long dependent) {
        return dependentToUnderlying.get(dependent);
    }

    public synchronized void addUnderlying(long dependent, long underlying) {
        dependentToUnderlying.put(dependent,underlying);
    }

}
