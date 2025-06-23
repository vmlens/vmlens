package com.vmlens.trace.agent.bootstrap.interleave.deadlock;

import com.vmlens.trace.agent.bootstrap.interleave.Position;


public class PositionPair {

    private final Position parent;
    private final Position child;

    public PositionPair(Position parent, Position child) {
        this.parent = parent;
        this.child = child;
    }


}
