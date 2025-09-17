package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToMaxPosition;
import com.vmlens.trace.agent.bootstrap.interleave.loop.NormalizeContext;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class ThreadJoin extends InterleaveActionForInDependentBlock {

    private final int threadIndex;
    private final int joinedThreadIndex;

    public ThreadJoin(int threadIndex, int joinedThreadIndex) {
        this.threadIndex = threadIndex;
        this.joinedThreadIndex = joinedThreadIndex;
    }

    @Override
    public void addFixedOrder(Position myPosition, TLinkedList<TLinkableWrapper<LeftBeforeRight>> result, ThreadIndexToMaxPosition threadIndexToMaxPosition) {
        int lastPosition = threadIndexToMaxPosition.getPositionAtThreadIndex(joinedThreadIndex);
        if(lastPosition > 0) {
            result.add(wrap(new LeftBeforeRight(new Position(joinedThreadIndex, lastPosition - 1),
                    myPosition)));
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

    @Override
    public boolean equalsNormalized(NormalizeContext normalizeContext, InterleaveAction other) {
        if(! (other instanceof ThreadJoin)) {
            return false;
        }
        ThreadJoin otherLock = (ThreadJoin) other;
        if(threadIndex != otherLock.threadIndex)  {
            return false;
        }

        return joinedThreadIndex == otherLock.joinedThreadIndex;
    }

    @Override
    public boolean startsThread() {
        return false;
    }
}
