package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTree;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContext;

public class TreeBuilder {

    private final StartNode start = new StartNode();

    public StartNode start() {
        return start;
    }

    public OrderTree build(InterleaveLoopContext interleaveLoopContext) {
        NodeBuilder current = start.getNext();
        ListElement previous = null;
        ListElement startElement =  null;
        int alternatingOrders = 0;
        while(current != null && alternatingOrders < interleaveLoopContext.maximumAlternatingOrders()) {
            ListElement currentListElement = current.build();
            if(startElement == null) {
                startElement = currentListElement;
            }
            if(previous != null) {
                previous.setNext(currentListElement);
            }
            previous = currentListElement;
            current = current.getNext();
            alternatingOrders++;
        }
        return new OrderTree(startElement);
    }
}
