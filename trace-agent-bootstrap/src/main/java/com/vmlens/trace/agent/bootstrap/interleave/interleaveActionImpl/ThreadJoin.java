package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl;

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
        int lastPosition = threadIndexToMaxPosition.getPositionAtThreadIndex(joinedThreadIndex);
        if(lastPosition > 0) {
            orderArraysBuilder.addFixedOrder(new LeftBeforeRight(new Position(joinedThreadIndex, lastPosition - 1),
                    myPosition));
        }


    }

    @Override
    public int threadIndex() {
        return threadIndex;
    }

    @Override
    public String toString() {
        return "ThreadJoin{" +
                "threadIndex=" + threadIndex +
                ", joinedThreadIndex=" + joinedThreadIndex +
                '}';
    }
}
