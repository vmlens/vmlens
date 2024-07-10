package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.InDependentBlock;
import com.vmlens.trace.agent.bootstrap.interleave.block.MapOfBlocks;
import com.vmlens.trace.agent.bootstrap.interleave.block.OrderArraysBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToMaxPosition;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public class ThreadJoin implements InterleaveAction, InDependentBlock {

    private static final Object SINGELTON_KEY = new Object();
    private final int joinedThreadIndex;

    public ThreadJoin(int joinedThreadIndex) {
        this.joinedThreadIndex = joinedThreadIndex;
    }

    @Override
    public Object blockBuilderKey() {
        return SINGELTON_KEY;
    }

    @Override
    public void blockBuilderAdd(Position myPosition, MapOfBlocks result) {
        result.addInDependent(new ElementAndPosition<InDependentBlock>(this, myPosition));
    }

    @Override
    public void addFixedOrder(Position myPosition, OrderArraysBuilder orderArraysBuilder, ThreadIndexToMaxPosition threadIndexToMaxPosition) {
        orderArraysBuilder.addFixedOrder(new LeftBeforeRight(new Position(joinedThreadIndex, threadIndexToMaxPosition.getPositionAtThreadIndex(joinedThreadIndex)), myPosition));
    }
}
