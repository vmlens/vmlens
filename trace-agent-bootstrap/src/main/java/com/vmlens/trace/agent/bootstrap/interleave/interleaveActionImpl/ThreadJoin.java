package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.*;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public class ThreadJoin implements InterleaveAction, InDependentBlock {
    private final int joinedThreadIndex;

    public ThreadJoin(int joinedThreadIndex) {
        this.joinedThreadIndex = joinedThreadIndex;
    }


    @Override
    public void blockBuilderAdd(Position myPosition,
                                MapContainingStack mapContainingStack,
                                MapOfBlocks result) {
        result.addInDependent(new ElementAndPosition<InDependentBlock>(this, myPosition));
    }

    @Override
    public void addFixedOrder(Position myPosition, OrderArraysBuilder orderArraysBuilder, ThreadIndexToMaxPosition threadIndexToMaxPosition) {
        orderArraysBuilder.addFixedOrder(new LeftBeforeRight(new Position(joinedThreadIndex, threadIndexToMaxPosition.getPositionAtThreadIndex(joinedThreadIndex)), myPosition));
    }
}
