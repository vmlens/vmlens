package com.vmlens.trace.agent.bootstrap.lock;

import com.vmlens.trace.agent.bootstrap.interleave.lock.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.lock.ReadWriteLockKey;
import com.vmlens.trace.agent.bootstrap.interleave.lock.ReentrantLockKey;

import static com.vmlens.trace.agent.bootstrap.interleave.lock.ReadWriteLockKey.readKey;

public class ReadLock extends ReadOrWriteLock {

    private final int id;

    ReadLock(int id) {
        this.id = id;
    }

    @Override
    public LockKey create(long objectHashCode) {
        return readKey(objectHashCode);
    }

    @Override
    public int id() {
        return id;
    }

}
