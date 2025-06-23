package com.vmlens.trace.agent.bootstrap.interleave.lockorconditioncontainer;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.interleavetypes.AddToAlternatingOrder;

public class BlockEnd {

    private final Position position;
    private final BlockEndOperation operation;

    public BlockEnd(Position position, BlockEndOperation operation) {
        this.position = position;
        this.operation = operation;
    }

    AddToAlternatingOrder createTuple(BlockStart blockEnd) {

    }

}
