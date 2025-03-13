package com.vmlens.trace.agent.bootstrap.interleave.lock;


public abstract class LockKey implements Comparable<LockKey> {

    public static final int CATEGORY_MONITOR = 1;

    public abstract int category();
    public abstract long objectHashCode();
    public abstract boolean isReadLock();

    public final boolean blocks(LockKey otherLock) {
        return ! (isReadLock() && otherLock.isReadLock());
    }

    @Override
    public final int compareTo(LockKey other) {
        if(category() != other.category()) {
            return Integer.compare(category(),other.category());
        }
        return Long.compare(objectHashCode(),other.objectHashCode());
    }

    @Override
    public final boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        LockKey lockKey = (LockKey) o;
        return category() == lockKey.category() && objectHashCode() == lockKey.objectHashCode();
    }

    @Override
    public final int hashCode() {
        int result = category();
        result = 31 * result + Long.hashCode(objectHashCode());
        return result;
    }
}
