package com.vmlens.trace.agent.bootstrap.lock;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.ReadWriteLockKey;

public abstract class ReadOrWriteLock implements LockType {

    public long getObjectHashCode(ReadWriteLockMap readWriteLockMap, long objectHashCode) {
        return  readWriteLockMap.getUnderlying(objectHashCode);
    }

    @Override
    public LockKey create(long objectHashCode) {
        return new ReadWriteLockKey(objectHashCode);
    }

}
