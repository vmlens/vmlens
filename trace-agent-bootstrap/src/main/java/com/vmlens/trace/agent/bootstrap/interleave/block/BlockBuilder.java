package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.Position;


public interface BlockBuilder {
    void blockBuilderAdd(Position myPosition, MapContainingStack mapContainingStack, MapOfBlocks result);
}
