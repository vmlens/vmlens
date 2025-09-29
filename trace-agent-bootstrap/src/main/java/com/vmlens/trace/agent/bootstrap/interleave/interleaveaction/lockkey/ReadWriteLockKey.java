package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey;

public class ReadWriteLockKey extends LockKey {

    private final boolean isRead;

    // visible for test
    public ReadWriteLockKey(long objectHashCode, boolean isRead) {
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

    @Override
    public String toString() {
        return "readWriteLock(" + isRead + "," + objectHashCode +"L)";
    }
}
