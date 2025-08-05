package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.lockkey;

public class MonitorKey extends LockKey {

    public MonitorKey(long objectHashCode) {
        super(objectHashCode);
    }

    @Override
    public int category() {
        return CATEGORY_MONITOR;
    }

    @Override
    public boolean isRead() {
        return false;
    }
}
