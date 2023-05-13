package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

/**
 * creates a map of block keys to thread ids to blocks from an actual run.
 */

public class BlockMapFactory {

    public BlockContainer create(
            TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>> actualRun) {
        KeyToThreadIdToElementList<Object, ElementAndPosition<BlockBuilder>> blockFactoryMap =
                new KeyToThreadIdToElementList<>();
        for (TLinkableWrapper<ElementAndPosition<BlockBuilder>> blockBuilder : actualRun) {
            blockFactoryMap.put(blockBuilder.element.element().blockBuilderKey(), blockBuilder.element);
        }
        BlockContainer result = new BlockContainer();
        for (ThreadIndexToElementList<ElementAndPosition<BlockBuilder>> threadIndexToElementList : blockFactoryMap) {
            for (TLinkableWrapper<TLinkedList<TLinkableWrapper<ElementAndPosition<BlockBuilder>>>> oneThread : threadIndexToElementList) {
                ElementAndPosition<BlockBuilder> prevoius = null;
                for (TLinkableWrapper<ElementAndPosition<BlockBuilder>> current : oneThread.element) {
                    if (prevoius == null) {
                        current.element.element().blockBuilderStart(current.element.position(), result);
                    } else {
                        prevoius.element().blockBuilderAdd(prevoius.position(), current.element, result);
                    }
                    prevoius = current.element;
                }
            }
        }
        return result;
    }
}
