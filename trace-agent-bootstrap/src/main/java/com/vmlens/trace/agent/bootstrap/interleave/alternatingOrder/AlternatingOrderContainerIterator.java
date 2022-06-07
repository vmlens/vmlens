package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

public class AlternatingOrderContainerIterator implements Iterator<CalculatedRun> {

    private final AlternatingOrderContainer container;
    private final AlternatingOrderIterator alternatingOrderIterator;
    private CalculatedRun next;

    public AlternatingOrderContainerIterator(AlternatingOrderContainer container) {
        this.container = container;
        this.alternatingOrderIterator = new AlternatingOrderIterator(container.fixedOrders,
                container.optionalAlternatingOrderElements);
    }

    public boolean hasNext() {
        while (next == null && alternatingOrderIterator.hasNext()) {
            TLinkedList<TLinkableWrapper<LeftBeforeRight>> order = alternatingOrderIterator.next();
            TLinkedList<TLinkableWrapper<Position>> orderedPositions =
                    new CreateCalculatedRunForOrder(order, container.length).create();
            if(orderedPositions != null ) {
                // ToDo hier info loggen
                container.logger.interleaveInfo(AlternatingOrderContainerIterator.class, calculatedRunToString(orderedPositions));
                next = new CalculatedRun(orderedPositions);
            }
        }
        if (next != null) {
            return true;
        }
        return false;
    }

    private String calculatedRunToString(TLinkedList<TLinkableWrapper<Position>> orderedPositions) {
        boolean isFirst = true;
        String result = "calculatedRun(threadIndex(";
        for (TLinkableWrapper<Position> position : orderedPositions) {
            if (!isFirst) {
                result += ",";
            }
            isFirst = false;
            result += position.element.threadIndex;
        }
        return result + "))";
    }

    public CalculatedRun next() {
        CalculatedRun temp = next;
        next = null;
        return temp;
    }

    @Override
    public void remove() {
        throw new RuntimeException("Not implemented");
    }

}
