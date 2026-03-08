package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderAlternative;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderListElement;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class Either extends StartOrEither implements NodeBuilder {

    private final OrderAlternative orderAlternativeA;
    private final OrderAlternative orderAlternativeB;

    public Either(TLinkedList<TLinkableWrapper<NodeBuilder>> nodeBuilderList,
                  OrderAlternative orderAlternativeA,
                  OrderAlternative orderAlternativeB) {
        super(nodeBuilderList);
        this.orderAlternativeA = orderAlternativeA;
        this.orderAlternativeB = orderAlternativeB;
    }

    @Override
    public OrderListElement build() {
        TLinkedList<TLinkableWrapper<OrderAlternative>> alternatives = new TLinkedList<>();
        alternatives.add(TLinkableWrapper.wrap(orderAlternativeA));
        alternatives.add(TLinkableWrapper.wrap(orderAlternativeB));
        return new OrderListElement(alternatives);
    }
}
