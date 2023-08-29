package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;


public interface BlockBuilder {
    Object blockBuilderKey();

    void blockBuilderAdd(Position myPosition, BlockContainer result);


}
