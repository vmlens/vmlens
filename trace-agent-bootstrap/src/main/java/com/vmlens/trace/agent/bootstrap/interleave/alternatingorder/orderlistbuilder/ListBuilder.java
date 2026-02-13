package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.ListElement;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderList;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContext;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.TLinkable;
import gnu.trove.list.linked.TLinkedList;

public class ListBuilder {

    private final TLinkedList<TLinkableWrapper<NodeBuilder>> nodeBuilderList = new TLinkedList<>();



    public StartNode start() {
        return start;
    }

    public OrderList build(InterleaveLoopContext interleaveLoopContext) {
        NodeBuilder current = start.getNext();
        ListElement previous = null;
        ListElement startElement =  null;
        while(current != null) {
            ListElement currentListElement = current.build();
            if(startElement == null) {
                startElement = currentListElement;
            }
            if(previous != null) {
                previous.setNext(currentListElement);
            }
            previous = currentListElement;
            current = current.getNext();
        }
        return new OrderList(startElement);
    }




}
