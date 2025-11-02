package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey;

public class ReentrantLockKey extends LockKey {

    public ReentrantLockKey(long objectHashCode) {
        super(objectHashCode);
    }

    @Override
    public int category() {
        return CATEGORY_REENTRANT_LOCK;
    }

    @Override
    public String toString() {
        return "lock(" + objectHashCode + "L)";
    }
}
