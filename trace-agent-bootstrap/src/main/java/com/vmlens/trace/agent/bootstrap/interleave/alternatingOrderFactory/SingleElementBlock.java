package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrderFactory;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class SingleElementBlock<T extends OrderElementFactory<T>> implements Block<SingleElementBlock<T> > {

    private final OrderElementFactoryAndPosition<T> orderElementFactoryAndPosition;

    public SingleElementBlock(OrderElementFactoryAndPosition<T> orderElementFactoryAndPosition) {
        this.orderElementFactoryAndPosition = orderElementFactoryAndPosition;
    }

    @Override
    public int threadIndex() {
        return orderElementFactoryAndPosition.position.threadIndex;
    }

    @Override
    public void createOrder(SingleElementBlock<T> otherBlock,
                            DeadlockContext deadlockContext,
                            TLinkedList<TLinkableWrapper<LeftBeforeRight>> fixedOrderElements,
                            TLinkedList<TLinkableWrapper<AlternatingOrderElement>> alternatingOrderElements) {
        orderElementFactoryAndPosition.orderElementFactory.createOrder(orderElementFactoryAndPosition.position,
                otherBlock.orderElementFactoryAndPosition,deadlockContext,
                fixedOrderElements,alternatingOrderElements);
    }
}
