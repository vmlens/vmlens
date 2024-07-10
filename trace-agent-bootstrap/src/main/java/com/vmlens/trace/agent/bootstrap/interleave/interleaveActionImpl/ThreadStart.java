package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.InDependentBlock;
import com.vmlens.trace.agent.bootstrap.interleave.block.MapOfBlocks;
import com.vmlens.trace.agent.bootstrap.interleave.block.OrderArraysBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToMaxPosition;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public class ThreadStart implements InterleaveAction, InDependentBlock {
    private static final Object SINGELTON_KEY = new Object();
    private final int startedThreadIndex;

    public ThreadStart(int startedThreadIndex) {
        this.startedThreadIndex = startedThreadIndex;
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
        orderArraysBuilder.addFixedOrder(new LeftBeforeRight(myPosition, new Position(startedThreadIndex, 0)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThreadStart that = (ThreadStart) o;

        return startedThreadIndex == that.startedThreadIndex;
    }
    @Override
    public int hashCode() {
        return startedThreadIndex;
    }
}
