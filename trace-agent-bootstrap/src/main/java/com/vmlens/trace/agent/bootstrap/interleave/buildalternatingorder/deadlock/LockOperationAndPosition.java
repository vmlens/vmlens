package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.deadlock;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.activelock.LockStartOperation;

public class LockOperationAndPosition {

    private final LockStartOperation operation;
    private final Position position;

    public LockOperationAndPosition(LockStartOperation operation, Position position) {
        this.operation = operation;
        this.position = position;
    }

    public Position position() {
        return position;
    }
}
