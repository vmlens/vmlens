package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.OrderTreeBuilderWrapper;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIndexToMaxPosition;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;
import gnu.trove.iterator.TIntIterator;
import gnu.trove.list.linked.TIntLinkedList;

public class MultiJoin extends InterleaveActionForInDependentBlock  {

    private final int threadIndex;
    private final TIntLinkedList joinedThreadIndices;

    public MultiJoin(int threadIndex, TIntLinkedList joinedThreadIndices) {
        this.threadIndex = threadIndex;
        this.joinedThreadIndices = joinedThreadIndices;
    }

    @Override
    public void addFixedOrder(Position myPosition, OrderTreeBuilderWrapper orderArraysBuilder, ThreadIndexToMaxPosition threadIndexToMaxPosition) {
        TIntIterator iter = joinedThreadIndices.iterator();
        while(iter.hasNext()) {
            int joinedThreadIndex = iter.next();
            int lastPosition = threadIndexToMaxPosition.getPositionAtThreadIndex(joinedThreadIndex);
            if(lastPosition > 0) {
                orderArraysBuilder.addFixedOrder(new LeftBeforeRight(new Position(joinedThreadIndex, lastPosition - 1),
                        myPosition));
            }
        }

    }

    @Override
    public boolean equalsNormalized(NormalizeContext normalizeContext, InterleaveAction other) {
        if(! (other instanceof MultiJoin)) {
            return false;
        }
        MultiJoin otherLock = (MultiJoin) other;
        return threadIndex == otherLock.threadIndex;
    }

    @Override
    public int threadIndex() {
        return threadIndex;
    }
}