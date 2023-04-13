package com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRightOld;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElementOld;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public interface OrderElementFactory<T>   {
    void createOrder(Position myPosition, OrderElementFactoryAndPosition<T> other,
                     DeadlockContext deadlockContext,
                     TLinkedList<TLinkableWrapper<LeftBeforeRightOld>> fixedOrderElements,
                     TLinkedList<TLinkableWrapper<AlternatingOrderElementOld>> alternatingOrderElements);
}
