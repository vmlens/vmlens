package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.lockkey.LockKey;

public class LockEnterOperation implements LockStartOperation {

    private final Position position;
    private final LockKey key;
    private final boolean isRead;

    public LockEnterOperation(Position position, LockKey key, boolean isRead) {
        this.position = position;
        this.key = key;
        this.isRead = isRead;
    }

    @Override
    public Position position() {
        return position;
    }

    @Override
    public LockKey key() {
        return key;
    }

    public boolean isRead() {
        return isRead;
    }

    @Override
    public int threadIndex() {
        return position.threadIndex();
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
