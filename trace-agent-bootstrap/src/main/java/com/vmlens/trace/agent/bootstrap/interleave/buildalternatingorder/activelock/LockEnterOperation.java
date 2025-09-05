package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.activelock;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;

public class LockEnterOperation implements LockStartOperation {

    private final Position position;
    private final LockKey key;

    public LockEnterOperation(Position position, LockKey key) {
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

    @Override
    public boolean isReadLock() {
        return key.isRead();
    }

    @Override
    public boolean canBeDeadlockParent() {
        return true;
    }

    @Override
    public boolean canBeDeadlockChild() {
        return true;
    }
}
