package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToMaxPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class ThreadJoin extends InterleaveActionForInDependentBlock {

    private final MethodIdByteCodePositionAndThreadIndex methodIdByteCodePositionAndThreadIndex;
    private final int joinedThreadIndex;

    public ThreadJoin(MethodIdByteCodePositionAndThreadIndex methodIdByteCodePositionAndThreadIndex, int joinedThreadIndex) {
        this.methodIdByteCodePositionAndThreadIndex = methodIdByteCodePositionAndThreadIndex;
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
        return methodIdByteCodePositionAndThreadIndex.threadIndex();
    }

    @Override
    public String toString() {
        return "ThreadJoin{" +
                "threadIndex=" + methodIdByteCodePositionAndThreadIndex.threadIndex() +
                ", joinedThreadIndex=" + joinedThreadIndex +
                '}';
    }

    @Override
    public boolean equalsNormalized(InterleaveAction other) {
        if(! (other instanceof ThreadJoin)) {
            return false;
        }
        ThreadJoin otherLock = (ThreadJoin) other;
        if(! methodIdByteCodePositionAndThreadIndex.equals(otherLock.methodIdByteCodePositionAndThreadIndex))  {
            return false;
        }

        return joinedThreadIndex == otherLock.joinedThreadIndex;
    }

}
