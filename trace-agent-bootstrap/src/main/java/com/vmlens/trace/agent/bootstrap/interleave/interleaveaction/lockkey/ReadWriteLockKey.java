package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey;

public class ReadWriteLockKey extends LockKey {

    // visible for test
    public ReadWriteLockKey(long objectHashCode) {
        super(objectHashCode);

    }

    @Override
    public int category() {
        return CATEGORY_READ_WRITE_LOCK;
    }

    @Override
    public String toString() {
        return "readWriteLock(" + objectHashCode +"L)";
    }
}
