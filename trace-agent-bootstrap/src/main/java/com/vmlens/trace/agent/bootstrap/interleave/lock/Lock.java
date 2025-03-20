package com.vmlens.trace.agent.bootstrap.interleave.lock;

public class Lock {
    private final LockKey lockKey;

    public Lock(LockKey lockKey) {
        this.lockKey = lockKey;
    }

    public LockKey key() {
        return lockKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lock monitor = (Lock) o;
        return lockKey.equals(monitor.lockKey);
    }

    @Override
    public int hashCode() {
        return lockKey.hashCode();
    }


    public boolean startsAlternatingOrder(Lock other) {
        // if both keys are read they do not start an alternating order
        if(lockKey.isRead() && other.lockKey.isRead()) {
            return false;
        }
        return true;
    }
}
