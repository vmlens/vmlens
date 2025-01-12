package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.OrderArraysBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToMaxPosition;

public class ThreadJoin extends InterleaveActionForInDependentBlock {

    private final int threadIndex;
    private final int joinedThreadIndex;

    public ThreadJoin(int threadIndex, int joinedThreadIndex) {
        this.threadIndex = threadIndex;
        this.joinedThreadIndex = joinedThreadIndex;
    }

    @Override
    public void addFixedOrder(Position myPosition, OrderArraysBuilder orderArraysBuilder, ThreadIndexToMaxPosition threadIndexToMaxPosition) {
        orderArraysBuilder.addFixedOrder(new LeftBeforeRight(new Position(joinedThreadIndex, threadIndexToMaxPosition.getPositionAtThreadIndex(joinedThreadIndex)), myPosition));
    }

    @Override
    public int threadIndex() {
        return threadIndex;
    }
}
