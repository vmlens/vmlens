package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.Position;

public interface IndependentBlock {
    void addFixedOrder(Position myPosition, OrderTreeBuilderWrapper orderArraysBuilder,
                       ThreadIndexToMaxPosition threadIndexToMaxPosition);
}
