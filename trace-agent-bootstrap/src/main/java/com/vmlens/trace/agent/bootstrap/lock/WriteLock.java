package com.vmlens.trace.agent.bootstrap.lock;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;

import static com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.ReadWriteLockKey.writeKey;

public class WriteLock extends ReadOrWriteLock {

   private final int id;

   WriteLock(int id) {
        this.id = id;
    }

    @Override
    public LockKey create(long objectHashCode) {
        return writeKey(objectHashCode);
    }

    @Override
    public int id() {
        return id;
    }
}
