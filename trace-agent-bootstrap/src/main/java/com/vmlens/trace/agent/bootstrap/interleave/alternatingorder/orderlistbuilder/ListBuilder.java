package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderList;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderListElement;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContext;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class ListBuilder {

    private final TLinkedList<TLinkableWrapper<NodeBuilder>> nodeBuilderList = new TLinkedList<>();

    public StartNode start() {
        return new StartNode(nodeBuilderList);
    }

    public OrderList build(InterleaveLoopContext interleaveLoopContext) {
        return new OrderList(buildList());
    }

    TLinkedList<TLinkableWrapper<OrderListElement>> buildList() {
        TLinkedList<TLinkableWrapper<OrderListElement>> result = new TLinkedList<>();
        for(TLinkableWrapper<NodeBuilder> nodeBuilder : nodeBuilderList) {
            result.add(TLinkableWrapper.wrap(nodeBuilder.element().build()));
        }

        return  result;
    }




}
