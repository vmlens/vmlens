package com.vmlens.trace.agent.bootstrap.interleave.block.guineapig;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.IndependentBlock;
import com.vmlens.trace.agent.bootstrap.interleave.block.OrderArraysBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToMaxPosition;

public class IndependentBlockElementNoOpGuineaPig implements IndependentBlock {
    @Override
    public void addFixedOrder(Position myPosition,
                              OrderArraysBuilder orderArraysBuilder,
                              ThreadIndexToMaxPosition threadIndexToMaxPosition) {

    }
}
