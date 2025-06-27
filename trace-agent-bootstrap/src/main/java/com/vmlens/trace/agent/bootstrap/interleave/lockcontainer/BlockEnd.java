package com.vmlens.trace.agent.bootstrap.interleave.lockcontainer;

import com.vmlens.trace.agent.bootstrap.interleave.Position;

public class BlockEnd {

    private final Position position;
    private final BlockEndOperation operation;

    public BlockEnd(Position position, BlockEndOperation operation) {
        this.position = position;
        this.operation = operation;
    }


    public Position position() {
        return position;
    }
}
