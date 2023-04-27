package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

public class OrderArraysFactory {

    public OrderArrays create(TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>> blockBuilderList) {
        BlockContainer blockMap =
                new BlockMapFactory().create(blockBuilderList);
        return create(blockMap);
    }

    private OrderArrays create(BlockContainer blockMap) {
        OrderArraysBuilder builder = new OrderArraysBuilder();
        for(ThreadIdToElementList<DependentBlock> threadIdToElementList : blockMap.dependentBlocks()) {
            for(TLinkableWrapper<TLinkedList<TLinkableWrapper<DependentBlock>>> oneThread : threadIdToElementList) {
                for(TLinkableWrapper<DependentBlock> current : oneThread.element) {
                    Iterator<TLinkableWrapper<TLinkedList<TLinkableWrapper<DependentBlock>>>> otherThreadBlocks =
                            threadIdToElementList.iteratorAt(current.element.threadIndex()+1);
                    while(otherThreadBlocks.hasNext()) {
                        TLinkedList<TLinkableWrapper<DependentBlock>> otherThread = otherThreadBlocks.next().element;
                        // ToDo we should probably stop when more than n elements were created
                        for(TLinkableWrapper<DependentBlock> otherBlock : otherThread) {
                            current.element.addToAlternatingOrderContainerBuilder(otherBlock.element,builder);
                        }
                    }
                }
            }
        }
        for(TLinkableWrapper<ElementAndPosition<InDependentBlockElement>> independent : blockMap.inDependentBlocks()) {
            independent.element.element().addToAlternatingOrderContainerBuilder(independent.element.position(),builder);
        }
        return builder.build();
    }
}
