package com.vmlens.trace.agent.bootstrap.interleave.lockorconditioncontainer;

import com.vmlens.trace.agent.bootstrap.interleave.Position;

public class BlockStart {

    private final Position position;
    private final BlockStartOperation operation;

    public BlockStart(Position position, BlockStartOperation operation) {
        this.position = position;
        this.operation = operation;
    }

    public Position position() {
        return position;
    }

    public boolean isReadLock() {
       return operation.isReadLock();
    }

}
