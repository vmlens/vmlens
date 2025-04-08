package com.vmlens.trace.agent.bootstrap.lock;

import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.lock.ReentrantLockKey;

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
}
