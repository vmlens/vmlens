package com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class BlockList<T extends Block<T>> {

    private final TLinkedList<TLinkableWrapper<T>> list = new TLinkedList<TLinkableWrapper<T>>();

    public void createOrder(
            DeadlockContext deadlockContext,
            TLinkedList<TLinkableWrapper<LeftBeforeRight>> fixedOrderElements,
            TLinkedList<TLinkableWrapper<AlternatingOrderElement>> alternatingOrderElements) {
        TLinkableWrapper<T>[] blockArray = list.toUnlinkedArray(new TLinkableWrapper[0]);
        for (int outerIndex = 0; outerIndex < blockArray.length; outerIndex++) {
            for (int innerIndex = outerIndex + 1; innerIndex < blockArray.length; innerIndex++) {
                T outer = blockArray[outerIndex].element;
                T inner = blockArray[innerIndex].element;
                if (outer.threadIndex() != inner.threadIndex()) {
                    outer.createOrder(inner, deadlockContext,
                            fixedOrderElements, alternatingOrderElements);
                }
            }
        }
    }

    public void add(T block) {
        list.add(new TLinkableWrapper(block));
    }

}
