package com.vmlens.trace.agent.bootstrap.lock;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.StampedLockKey;

public class StampedLock implements LockType{

    private final int id;
    private final boolean isRead;

    StampedLock(int id,
                       boolean isRead) {
        this.id = id;
        this.isRead = isRead;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public LockKey create(long objectHashCode) {
        return new StampedLockKey(objectHashCode);
    }

    @Override
    public long getObjectHashCode(ReadWriteLockMap readWriteLockMap, long objectHashCode) {
        return objectHashCode;
    }

    @Override
    public boolean isRead() {
        return isRead;
    }
}
