package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.OrderTreeBuilderWrapper;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToMaxPosition;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;

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
                              OrderTreeBuilderWrapper orderArraysBuilder,
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

    @Override
    public boolean equalsNormalized(NormalizeContext normalizeContext, InterleaveAction other) {
        if(! (other instanceof ThreadStart)) {
            return false;
        }
        ThreadStart otherLock = (ThreadStart) other;
        if(threadIndex != otherLock.threadIndex)  {
            return false;
        }

        return startedThreadIndex == otherLock.startedThreadIndex;
    }
}
