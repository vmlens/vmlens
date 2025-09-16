package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToMaxPosition;
import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.iterator.TIntIterator;
import gnu.trove.list.linked.TIntLinkedList;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class MultiJoin extends InterleaveActionForInDependentBlock  {

    private final int threadIndex;
    private final TIntLinkedList joinedThreadIndices;

    public MultiJoin(int threadIndex, TIntLinkedList joinedThreadIndices) {
        this.threadIndex = threadIndex;
        this.joinedThreadIndices = joinedThreadIndices;
    }

    @Override
    public void addFixedOrder(Position myPosition, TLinkedList<TLinkableWrapper<LeftBeforeRight>> result, ThreadIndexToMaxPosition threadIndexToMaxPosition) {
        TIntIterator iter = joinedThreadIndices.iterator();
        while(iter.hasNext()) {
            int joinedThreadIndex = iter.next();
            int lastPosition = threadIndexToMaxPosition.getPositionAtThreadIndex(joinedThreadIndex);
            if(lastPosition > 0) {
                result.add(wrap(new LeftBeforeRight(new Position(joinedThreadIndex, lastPosition - 1),
                        myPosition)));
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

    @Override
    public boolean startsThread() {
        return false;
    }
}