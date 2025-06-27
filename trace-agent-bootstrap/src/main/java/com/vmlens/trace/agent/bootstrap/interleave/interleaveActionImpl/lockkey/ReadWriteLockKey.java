package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.lockkey;

public class ReadWriteLockKey extends LockKey {

    private final boolean isRead;

    private ReadWriteLockKey(long objectHashCode, boolean isRead) {
        super(objectHashCode);
        this.isRead = isRead;
    }

    public static ReadWriteLockKey readKey(long objectHashCode)  {
       return new ReadWriteLockKey(objectHashCode,true);
    }

    public static ReadWriteLockKey writeKey(long objectHashCode)  {
        return new ReadWriteLockKey(objectHashCode,false);
    }

    @Override
    public int category() {
        return CATEGORY_READ_WRITE_LOCK;
    }

    @Override
    public boolean isRead() {
        return isRead;
    }
}
