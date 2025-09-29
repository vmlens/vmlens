package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.threadindexcollection.ThreadIndexToMaxPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class ThreadStart extends InterleaveActionForInDependentBlock {

    private final MethodIdByteCodePositionAndThreadIndex methodIdByteCodePositionAndThreadIndex;
    private final int startedThreadIndex;

    public ThreadStart(MethodIdByteCodePositionAndThreadIndex methodIdByteCodePositionAndThreadIndex,
                       int startedThreadIndex) {
        this.methodIdByteCodePositionAndThreadIndex = methodIdByteCodePositionAndThreadIndex;
        this.startedThreadIndex = startedThreadIndex;
    }

    @Override
    public int threadIndex() {
        return methodIdByteCodePositionAndThreadIndex.threadIndex();
    }

    // Visible for test
    public int startedThreadIndex() {
        return startedThreadIndex;
    }

    @Override
    public void addFixedOrder(Position myPosition,
                              TLinkedList<TLinkableWrapper<LeftBeforeRight>> result,
                              ThreadIndexToMaxPosition threadIndexToMaxPosition) {
        result.add(wrap(new LeftBeforeRight(myPosition, new Position(startedThreadIndex, 0))));
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
        return "threadStart(" +
                 methodIdByteCodePositionAndThreadIndex.threadIndex() +
                "," + startedThreadIndex +  ");";

    }

    @Override
    public boolean equalsNormalized(InterleaveAction other) {
        if(! (other instanceof ThreadStart)) {
            return false;
        }
        ThreadStart otherLock = (ThreadStart) other;
        if(! methodIdByteCodePositionAndThreadIndex.equals(otherLock.methodIdByteCodePositionAndThreadIndex))  {
            return false;
        }

        return startedThreadIndex == otherLock.startedThreadIndex;
    }

}
