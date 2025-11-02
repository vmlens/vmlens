package com.vmlens.trace.agent.bootstrap.lock;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;

public interface LockType {

    int id();
    LockKey create(long objectHashCode);
    long getObjectHashCode(ReadWriteLockMap readWriteLockMap, long objectHashCode);
    boolean isRead();

}
