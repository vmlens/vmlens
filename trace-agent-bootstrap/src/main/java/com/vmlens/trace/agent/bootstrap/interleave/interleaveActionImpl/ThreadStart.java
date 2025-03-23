package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.OrderArraysBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToMaxPosition;
import com.vmlens.trace.agent.bootstrap.interleave.deadlock.BlockingLockRelationBuilder;

public class ThreadStart extends InterleaveActionForInDependentBlock {
    private final int threadIndex;
    private final int startedThreadIndex;

    public ThreadStart(int threadIndex, int startedThreadIndex) {
        this.threadIndex = threadIndex;
        this.startedThreadIndex = startedThreadIndex;
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
    public void addFixedOrder(Position myPosition,
                              OrderArraysBuilder orderArraysBuilder,
                              ThreadIndexToMaxPosition threadIndexToMaxPosition) {
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

    @Override
    public String toString() {
        return "ThreadStart{" +
                "threadIndex=" + threadIndex +
                ", startedThreadIndex=" + startedThreadIndex +
                '}';
    }
}
