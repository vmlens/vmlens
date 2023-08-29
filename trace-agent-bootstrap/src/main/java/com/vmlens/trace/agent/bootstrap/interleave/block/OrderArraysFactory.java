package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

/**
 * Creates alternating and fixed orders for each block type. Blocks with different keys are independent.
 */
public class OrderArraysFactory {
    public OrderArrays create(TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>> blockBuilderList,
                              ThreadIndexToMaxPosition threadIndexToMaxPosition) {
        BlockContainer blockMap =
                new BlockContainerFactory().create(blockBuilderList);
        return create(blockMap, threadIndexToMaxPosition);
    }

    // Visible for Test
    OrderArrays create(BlockContainer blockMap, ThreadIndexToMaxPosition threadIndexToMaxPosition) {
        OrderArraysBuilder builder = new OrderArraysBuilder();
        for (ThreadIndexToElementList<DependentBlock> threadIndexToElementList : blockMap.dependentBlocks()) {
            for (TLinkableWrapper<TLinkedList<TLinkableWrapper<DependentBlock>>> oneThread : threadIndexToElementList) {
                for (TLinkableWrapper<DependentBlock> current : oneThread.element) {
                    Iterator<TLinkableWrapper<TLinkedList<TLinkableWrapper<DependentBlock>>>> otherThreadBlocks =
                            threadIndexToElementList.iteratorStartingAt(current.element.threadIndex() + 1);
                    while (otherThreadBlocks.hasNext()) {
                        TLinkedList<TLinkableWrapper<DependentBlock>> otherThread = otherThreadBlocks.next().element;
                        // ToDo we should probably stop when more than n elements were created
                        for (TLinkableWrapper<DependentBlock> otherBlock : otherThread) {
                            current.element.addToAlternatingOrderContainerBuilder(otherBlock.element, builder);
                        }
                    }
                }
            }
        }
        for (TLinkableWrapper<ElementAndPosition<InDependentBlock>> independent : blockMap.inDependentBlocks()) {
            independent.element.element().addToAlternatingOrderContainerBuilder(independent.element.position(), builder, threadIndexToMaxPosition);
        }
        return builder.build();
    }
}
