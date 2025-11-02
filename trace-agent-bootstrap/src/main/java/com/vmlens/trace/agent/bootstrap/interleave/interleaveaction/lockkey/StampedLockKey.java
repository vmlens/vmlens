package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey;

public class StampedLockKey extends LockKey {

    public StampedLockKey(long objectHashCode) {
        super(objectHashCode);
    }

    @Override
    public int category() {
        return STAMPED_LOCK;
    }

}
