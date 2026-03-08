package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist;


import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.cycle.OrderCycle;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public interface OrderAlternative   {

    void process(CreateOrderContext context);
    void addToCombinedAlternatives(TLinkedList<TLinkableWrapper<AlternativeOneOrder>> combinedAlternatives);
    boolean isPartOfCycle(OrderCycle orderCycle);

}
