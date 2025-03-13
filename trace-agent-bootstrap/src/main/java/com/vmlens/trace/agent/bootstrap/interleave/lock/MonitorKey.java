package com.vmlens.trace.agent.bootstrap.interleave.lock;

public class MonitorKey extends LockKey {

    private final long objectHashCode;

    public MonitorKey(long objectHashCode) {
        this.objectHashCode = objectHashCode;
    }


    @Override
    public int category() {
        return CATEGORY_MONITOR;
    }

    @Override
    public long objectHashCode() {
        return objectHashCode;
    }

    @Override
    public boolean isReadLock() {
        return false;
    }
}
