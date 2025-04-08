package com.vmlens.trace.agent.bootstrap.lock;

public abstract class ReadOrWriteLock implements LockType {

    public long getObjectHashCode(ReadWriteLockMap readWriteLockMap, long objectHashCode) {
        return  readWriteLockMap.getUnderlying(objectHashCode);
    }

}
