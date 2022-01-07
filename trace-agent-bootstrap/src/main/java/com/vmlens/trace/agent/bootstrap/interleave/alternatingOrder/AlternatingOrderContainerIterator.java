package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.domain.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.domain.Position;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class AlternatingOrderContainerIterator {

    private final AlternatingOrderContainer container;
    private final AlternatingOrderIterator alternatingOrderIterator;
    private CalculatedRun next;

    public AlternatingOrderContainerIterator(AlternatingOrderContainer container) {
        this.container = container;
        this.alternatingOrderIterator = new AlternatingOrderIterator(container.optionalAlternatingOrderElements);
    }

    public boolean hasNext() {
        while (next == null && alternatingOrderIterator.hasNext()) {
            LeftBeforeRight[] order = alternatingOrderIterator.next();
            TLinkedList<TLinkableWrapper<Position>> orderedPositions = new CreateCalculatedRunForOrder(order, container.length).create();
            if(orderedPositions != null ) {
                next = new CalculatedRun(orderedPositions);
            }
        }
        if (next != null) {
            return true;
        }
        return false;
    }

    public CalculatedRun next() {
        CalculatedRun temp = next;
        next = null;
        return temp;
    }

}
