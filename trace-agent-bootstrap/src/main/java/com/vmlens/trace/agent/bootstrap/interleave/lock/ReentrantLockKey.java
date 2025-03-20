package com.vmlens.trace.agent.bootstrap.interleave.lock;

public class ReentrantLockKey extends LockKey {

    public ReentrantLockKey(long objectHashCode) {
        super(objectHashCode);
    }

    @Override
    public int category() {
        return CATEGORY_REENTRANT_LOCK;
    }

    @Override
    public boolean isRead() {
        return false;
    }
}
