package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.deadlock;

import com.vmlens.trace.agent.bootstrap.interleave.Position;


public class PositionPair {

    private final Position parent;
    private final Position child;

    public PositionPair(Position parent, Position child) {
        this.parent = parent;
        this.child = child;
    }

    public Position parent() {
        return parent;
    }

    public Position child() {
        return child;
    }

    int threadIndex() {
        return parent.threadIndex();
    }

}
