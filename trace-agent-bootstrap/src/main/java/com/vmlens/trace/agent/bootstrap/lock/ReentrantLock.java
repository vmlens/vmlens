package com.vmlens.trace.agent.bootstrap.lock;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.ReentrantLockKey;

public class ReentrantLock implements LockType {

    private final int id;

    ReentrantLock(int id) {
        this.id = id;
    }

    @Override
    public LockKey create(long objectHashCode) {
        return new ReentrantLockKey(objectHashCode);
    }

    public long getObjectHashCode(ReadWriteLockMap readWriteLockMap, long objectHashCode) {
       return  objectHashCode;
    }

    @Override
    public int id() {
        return id;
    }

    @Override
    public boolean isRead() {
        return false;
    }
}
