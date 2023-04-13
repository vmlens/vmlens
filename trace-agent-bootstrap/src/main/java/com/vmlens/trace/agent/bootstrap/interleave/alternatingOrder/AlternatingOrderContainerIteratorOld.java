package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRightOld;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

public class AlternatingOrderContainerIteratorOld implements Iterator<CalculatedRunOld> {

    private final AlternatingOrderContainerOld container;
    private final AlternatingOrderIteratorOld alternatingOrderIterator;
    private CalculatedRunOld next;

    public AlternatingOrderContainerIteratorOld(AlternatingOrderContainerOld container) {
        this.container = container;
        this.alternatingOrderIterator = new AlternatingOrderIteratorOld(container.fixedOrders,
                container.optionalAlternatingOrderElements);
    }

    public boolean hasNext() {
        while (next == null && alternatingOrderIterator.hasNext()) {
            TLinkedList<TLinkableWrapper<LeftBeforeRightOld>> order = alternatingOrderIterator.next();
            TLinkedList<TLinkableWrapper<Position>> orderedPositions =
                    new CreateCalculatedRunForOrderOld(order, container.length).create();
            if(orderedPositions != null ) {
                // ToDo hier info loggen
                container.logger.interleaveInfo(AlternatingOrderContainerIteratorOld.class, calculatedRunToString(orderedPositions));
                next = new CalculatedRunOld(orderedPositions);
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

    public CalculatedRunOld next() {
        CalculatedRunOld temp = next;
        next = null;
        return temp;
    }

    @Override
    public void remove() {
        throw new RuntimeException("Not implemented");
    }

}
