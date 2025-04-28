package com.vmlens.trace.agent.bootstrap.interleave.block.dependent;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.element.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.block.OrderArraysBuilder;

class StrategyAlternating implements Strategy {
    @Override
    public void addToBuilder(DependentBlock myBlock,
                             DependentBlock blockFromOtherThread,
                             OrderArraysBuilder orderArraysBuilder) {
        orderArraysBuilder.addAlternatingOrder(new AlternatingOrderElement(
                myBlock.end.element().alternatingOrderElementStrategy(),
                new LeftBeforeRight(myBlock.end.position(), blockFromOtherThread.start.position()),
                new LeftBeforeRight(blockFromOtherThread.end.position(), myBlock.start.position())));

    }
}
