package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

/**
 * Creates blocks for each interleave action of an actual run. Interleave actions of different types are independent.
 */

public class MapOfBlocksExceptDeadlockFactory {

    public MapOfBlocks create(
            TLinkedList<TLinkableWrapper<ElementAndPosition<InterleaveAction>>> actualRun) {
        ThreadIndexToElementList<ElementAndPosition<InterleaveAction>> threadIndexToElementList = new
                ThreadIndexToElementList<>();
        for (TLinkableWrapper<ElementAndPosition<InterleaveAction>> blockBuilder : actualRun) {
            threadIndexToElementList.add(blockBuilder.element());
        }
        MapOfBlocks result = new MapOfBlocks();
        MapContainingStack mapContainingStack = new MapContainingStack();
        Iterator<TLinkableWrapper<TLinkedList<TLinkableWrapper<ElementAndPosition<InterleaveAction>>>>> multipleThreadsIterator =
                threadIndexToElementList.iterator();
        while (multipleThreadsIterator.hasNext()) {
            TLinkableWrapper<TLinkedList<TLinkableWrapper<ElementAndPosition<InterleaveAction>>>> thread =
                    multipleThreadsIterator.next();
            Iterator<TLinkableWrapper<ElementAndPosition<InterleaveAction>>> perThreadIterator = thread.element().iterator();
            while (perThreadIterator.hasNext()) {
                TLinkableWrapper<ElementAndPosition<InterleaveAction>> current = perThreadIterator.next();
                current.element().element().blockBuilderAdd(current.element().position(), mapContainingStack, result);
            }
        }
        return result;
    }
}
