package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;

public class WaitExitOperation implements LockStartOperation {

    private final Position position;
    private final LockKey key;


    public WaitExitOperation(Position position, LockKey key) {
        this.position = position;
        this.key = key;
    }

    @Override
    public Position position() {
        return position;
    }

    @Override
    public LockKey key() {
        return key;
    }

    @Override
    public int threadIndex() {
        return position.threadIndex();
    }


    /*
     * Read locks do not have conditions see
     * https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/locks/ReentrantReadWriteLock.ReadLock.html
     * newCondition() Throws UnsupportedOperationException because ReadLocks do not support conditions.
     * so we can be sure that we are in a write lock
     *
     */
    @Override
    public boolean isRead() {
        return false;
    }

    @Override
    public boolean canBeDeadlockParent() {
        return false;
    }

    @Override
    public boolean canBeDeadlockChild() {
        return false;
    }
}
