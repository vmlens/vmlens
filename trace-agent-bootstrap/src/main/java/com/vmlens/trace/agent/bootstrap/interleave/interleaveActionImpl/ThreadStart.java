package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.*;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public class ThreadStart implements InterleaveAction, IndependentBlock {
    private final int threadIndex;
    private final int startedThreadIndex;

    public ThreadStart(int threadIndex, int startedThreadIndex) {
        this.threadIndex = threadIndex;
        this.startedThreadIndex = startedThreadIndex;
    }


    @Override
    public void blockBuilderAdd(Position myPosition,
                                MapContainingStack mapContainingStack,
                                MapOfBlocks result) {
        result.addInDependent(new ElementAndPosition<IndependentBlock>(this, myPosition));
    }

    @Override
    public int threadIndex() {
        return threadIndex;
    }

    // Visible for test
    public int startedThreadIndex() {
        return startedThreadIndex;
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
