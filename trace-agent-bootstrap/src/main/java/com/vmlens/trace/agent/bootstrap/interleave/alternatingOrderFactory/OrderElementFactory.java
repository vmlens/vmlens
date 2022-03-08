package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrderFactory;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public interface OrderElementFactory<T>   {
    void createOrder(Position myPosition, OrderElementFactoryAndPosition<T> other,
                     DeadlockContext deadlockContext,
                     TLinkedList<TLinkableWrapper<LeftBeforeRight>> fixedOrderElements,
                     TLinkedList<TLinkableWrapper<AlternatingOrderElement>> alternatingOrderElements);
}
