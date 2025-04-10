package com.vmlens.trace.agent.bootstrap.lock;

import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;

public interface LockType {

    int id();
    LockKey create(long objectHashCode);
    long getObjectHashCode(ReadWriteLockMap readWriteLockMap, long objectHashCode);

}
