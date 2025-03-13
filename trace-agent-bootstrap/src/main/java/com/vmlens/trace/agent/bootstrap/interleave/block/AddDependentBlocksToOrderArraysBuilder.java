package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.block.dependent.DependentBlock;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

/**
 * Creates alternating and fixed orders for each block type. Blocks with different keys are independent.
 */
public class AddDependentBlocksToOrderArraysBuilder {
    public static void add(KeyToThreadIdToElementList<Object, DependentBlock> dependentBlocks,
                    OrderArraysBuilder builder) {
        for (ThreadIndexToElementList<DependentBlock> threadIndexToElementList : dependentBlocks) {
            add(threadIndexToElementList,builder);
        }
    }

    private static void add(ThreadIndexToElementList<DependentBlock> threadIndexToElementList,
                           OrderArraysBuilder builder) {
        for (TLinkableWrapper<TLinkedList<TLinkableWrapper<DependentBlock>>> oneThread : threadIndexToElementList) {
                for (TLinkableWrapper<DependentBlock> current : oneThread.element()) {
                    Iterator<TLinkableWrapper<TLinkedList<TLinkableWrapper<DependentBlock>>>> otherThreadBlocks =
                            threadIndexToElementList.iteratorStartingAt(current.element().threadIndex() + 1);
                    while (otherThreadBlocks.hasNext()) {
                        TLinkedList<TLinkableWrapper<DependentBlock>> otherThread = otherThreadBlocks.next().element();
                        // ToDo we should probably stop when more than n elements were created
                        for (TLinkableWrapper<DependentBlock> otherBlock : otherThread) {
                            current.element().addAlternatingOrder(otherBlock.element(), builder);
                        }
                    }
            }
        }
    }



}
