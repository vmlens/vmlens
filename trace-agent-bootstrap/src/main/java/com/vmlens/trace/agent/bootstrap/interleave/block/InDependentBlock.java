package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.Position;

public interface InDependentBlock {
    void addToAlternatingOrderContainerBuilder(Position myPosition, OrderArraysBuilder orderArraysBuilder,
                                               ThreadIndexToMaxPosition threadIndexToMaxPosition);
}
