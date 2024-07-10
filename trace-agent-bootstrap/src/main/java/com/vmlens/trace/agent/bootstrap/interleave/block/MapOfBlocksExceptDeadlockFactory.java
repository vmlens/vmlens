package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

/**
 * Creates blocks for each interleave action of an actual run. Interleave actions of different types are independent.
 */

public class MapOfBlocksExceptDeadlockFactory {

    public MapOfBlocks create(
            TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>> actualRun) {
        KeyToThreadIdToElementList<Object, ElementAndPosition<BlockBuilder>> blockFactoryMap =
                new KeyToThreadIdToElementList<>();
        for (TLinkableWrapper<ElementAndPosition<BlockBuilder>> blockBuilder : actualRun) {
            blockFactoryMap.put(blockBuilder.element.element().blockBuilderKey(), blockBuilder.element);
        }
        MapOfBlocks result = new MapOfBlocks();
        for (ThreadIndexToElementList<ElementAndPosition<BlockBuilder>> threadIndexToElementList : blockFactoryMap) {
            Iterator<TLinkableWrapper<TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>>>> multipleThreadsIterator = threadIndexToElementList.iterator();
            while (multipleThreadsIterator.hasNext()) {
                TLinkableWrapper<TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>>> thread = multipleThreadsIterator.next();
                Iterator<TLinkableWrapper<ElementAndPosition<BlockBuilder>>> perThreadIterator = thread.element.iterator();
                while (perThreadIterator.hasNext()) {
                    TLinkableWrapper<ElementAndPosition<BlockBuilder>> current = perThreadIterator.next();
                    current.element.element().blockBuilderAdd(current.element.position(), result);

                }
            }
        }
        return result;
    }
}
