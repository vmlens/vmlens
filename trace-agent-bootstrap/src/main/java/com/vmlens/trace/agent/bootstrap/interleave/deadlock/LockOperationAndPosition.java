package com.vmlens.trace.agent.bootstrap.interleave.deadlock;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.activelock.LockEnterOrTryLock;

public class LockOperationAndPosition {

    private final LockEnterOrTryLock operation;
    private final Position position;

    public LockOperationAndPosition(LockEnterOrTryLock operation, Position position) {
        this.operation = operation;
        this.position = position;
    }

    public Position position() {
        return position;
    }
}
