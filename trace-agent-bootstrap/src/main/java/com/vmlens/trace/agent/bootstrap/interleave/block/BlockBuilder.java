package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.Position;


public interface BlockBuilder {
    Object blockBuilderKey();

    void blockBuilderAdd(Position myPosition, MapOfBlocks result);


}
