package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class AddIndependentBlocksToOrderArraysBuilder {
    public void add(TLinkedList<TLinkableWrapper<ElementAndPosition<IndependentBlock>>> inDependentBlocks,
                    ThreadIndexToMaxPosition threadIndexToMaxPosition,
                    OrderArraysBuilder builder) {
        for (TLinkableWrapper<ElementAndPosition<IndependentBlock>> independent : inDependentBlocks) {
            independent.element().element().addFixedOrder(independent.element().position(), builder, threadIndexToMaxPosition);
        }
    }
}
