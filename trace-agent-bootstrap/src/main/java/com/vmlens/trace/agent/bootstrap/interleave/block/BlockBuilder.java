package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.ElementAndPosition;


public interface BlockBuilder  {
    Object blockBuilderKey();
    void blockBuilderStart(Position myPosition, BlockContainer result);
    void blockBuilderAdd(Position myPosition, ElementAndPosition<BlockBuilder> next, BlockContainer result);

}
