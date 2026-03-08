package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.cycle.OrderCycle;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class OrderListElement {

    private final TLinkedList<TLinkableWrapper<OrderAlternative>> alternativeList;

    public OrderListElement(TLinkedList<TLinkableWrapper<OrderAlternative>> alternativeList) {
        this.alternativeList = alternativeList;
    }

    public int numberOfAlternatives() {
        return alternativeList.size();
    }

    public void addOrder(CreateOrderContext context,int alternative) {
        alternativeList.get(alternative).element().process(context);
    }

    public void addToCombinedAlternatives(TLinkedList<TLinkableWrapper<AlternativeOneOrder>> result,int alternative) {
        alternativeList.get(alternative).element().addToCombinedAlternatives(result);
    }

    public boolean isPartOfCycle(OrderCycle orderCycle) {
        for(TLinkableWrapper<OrderAlternative> element : alternativeList) {
            if(element.element().isPartOfCycle(orderCycle)) {
                return true;
            }
        }
        return false;
    }



}
