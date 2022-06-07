package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.ThreadIndexContainer;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

public class CalculatedRun {
    private final Iterator<TLinkableWrapper<Position>> positions;
    private int activeThreadIndex = -1;

    public CalculatedRun(TLinkedList<TLinkableWrapper<Position>> positions) {
        this.positions = positions.iterator();
    }

    public CalculatedRun() {
        this.positions = new TLinkedList<TLinkableWrapper<Position>>().iterator();
    }

    public void advance() {
        if (positions.hasNext()) {
            activeThreadIndex = positions.next().element.threadIndex;
        }
    }

    public boolean needsToWait(int threadIndex, ThreadIndexContainer threadIndexContainer) {
        if (!threadIndexContainer.hasMoreThanOneThread()) {
            activeThreadIndex = -1;
            return false;
        }

        if (activeThreadIndex == -1) {
            advance();
        }
        if (threadIndexContainer.exists(activeThreadIndex)) {
            return activeThreadIndex != threadIndex;
        }

        activeThreadIndex = threadIndex;
        return false;
    }

}
