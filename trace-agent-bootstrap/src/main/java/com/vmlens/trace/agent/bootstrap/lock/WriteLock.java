package com.vmlens.trace.agent.bootstrap.lock;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.ReadWriteLockKey;

public class WriteLock extends ReadOrWriteLock {

   private final int id;

   WriteLock(int id) {
        this.id = id;
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
