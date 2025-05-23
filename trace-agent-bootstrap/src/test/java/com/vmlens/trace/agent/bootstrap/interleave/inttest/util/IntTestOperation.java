package com.vmlens.trace.agent.bootstrap.interleave.inttest.util;

import com.vmlens.trace.agent.bootstrap.interleave.Position;

public class IntTestOperation {

    private final Position position;

    @Override
    public String toString() {
        return "IntTestOperation{" +
                "position=" + position +
                '}';
    }

    public IntTestOperation(Position position) {
        this.position = position;
    }

    public Position asPosition() {
        return position;
    }

}
