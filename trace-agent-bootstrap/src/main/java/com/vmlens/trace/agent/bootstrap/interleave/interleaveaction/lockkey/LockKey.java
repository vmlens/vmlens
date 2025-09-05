package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey;


import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;

public abstract class LockKey implements Comparable<LockKey> {

    public static final int CATEGORY_MONITOR = 1;
    public static final int CATEGORY_REENTRANT_LOCK = 2;
    public static final int CATEGORY_READ_WRITE_LOCK = 3;

    private final long objectHashCode;

    public LockKey(long objectHashCode) {
        this.objectHashCode = objectHashCode;
    }

    public abstract int category();
    public abstract boolean isRead();

    public long objectHashCode() {
        return objectHashCode;
    }

    public boolean equalsNormalized(NormalizeContext normalizeContext, LockKey other) {
        if(category() != other.category()) {
            return false;
        }
        int myCode = normalizeContext.normalizeObjectHashCode(objectHashCode);
        int otherCode =   normalizeContext.normalizeObjectHashCode(other.objectHashCode);
        return myCode == otherCode;
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
