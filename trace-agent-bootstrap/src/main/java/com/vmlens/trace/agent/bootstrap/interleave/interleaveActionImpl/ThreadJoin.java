package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.*;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public class ThreadJoin implements InterleaveAction, InDependentBlockElement  {

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
    public void blockBuilderStart(Position myPosition, BlockContainer result) {
        result.addInDependent(new ElementAndPosition<InDependentBlockElement>(this,myPosition));
    }

    @Override
    public void blockBuilderAdd(Position myPosition, ElementAndPosition<BlockBuilder> next, BlockContainer result) {
        result.addInDependent(new ElementAndPosition<InDependentBlockElement>((ThreadStart)next.element(),next.position()));
    }

    @Override
    public void addToAlternatingOrderContainerBuilder(Position myPosition, OrderArraysBuilder orderArraysBuilder, ThreadIndexToMaxPosition threadIndexToMaxPosition) {
        orderArraysBuilder.addFixedOrder(new LeftBeforeRight(new Position(joinedThreadIndex,threadIndexToMaxPosition.getPositionAtThreadIndex(joinedThreadIndex)),myPosition));
    }
}
