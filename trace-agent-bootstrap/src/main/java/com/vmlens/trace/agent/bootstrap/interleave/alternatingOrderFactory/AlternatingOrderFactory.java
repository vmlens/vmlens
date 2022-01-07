package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrderFactory;


import com.vmlens.trace.agent.bootstrap.Logger;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.domain.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.domain.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.domain.SingleSyncActionBlock;
import com.vmlens.trace.agent.bootstrap.interleave.domain.SyncActionAndPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import java.util.Iterator;

import static java.lang.Math.max;

/**
 * creates a AlternativeOrderContainer from an actual run
 * Algorithm:
 * <ol>
 *     <li>SyncAction under lock, determine free Sync Actions</li>
 *     <li>Create Context for Deadlocks</li>
 *     <li>Create Blocks</li>
 *     <li>create AlternativeOrderContainer</li>
 * </ol>
 *
 * Sort for 1 - 3 by position in thread
 * Sort for 4 by block type
 *
 * loggen der einzelnen stufen z.B. SyncActionUnderLock und Blöcke
 *
 * filter for too many sync actions later
 * Fixme aufteilen, für jeden Schritt eine Klasse, Schritt 4 generisch, Interface.. klären für 1
 */

public class AlternatingOrderFactory {

    private final Logger logger;

    public AlternatingOrderFactory(Logger logger) {
        this.logger = logger;
    }

    public AlternatingOrderContainer create(TLinkedList<TLinkableWrapper<SyncActionAndPosition>> actualRun) {
        return new AlternatingOrderContainer(logger,
                createAlternatingOrderContainer(createSyncActionBlocks(actualRun)),
                calculateLength(actualRun));
    }

    TLinkedList<TLinkableWrapper<SingleSyncActionBlock>> createSyncActionBlocks
            (TLinkedList<TLinkableWrapper<SyncActionAndPosition>> actualRun) {
        Iterator<TLinkableWrapper<SyncActionAndPosition>> iterator = actualRun.iterator();
        TLinkedList<TLinkableWrapper<SingleSyncActionBlock>> result = new TLinkedList<TLinkableWrapper<SingleSyncActionBlock>>();
        while (iterator.hasNext()) {
            result.add(new TLinkableWrapper(iterator.next().element.createBlock()));
        }
        return result;
    }

    FixedAndAlternatingOrder createAlternatingOrderContainer
            (TLinkedList<TLinkableWrapper<SingleSyncActionBlock>> syncActionBlocks) {
        TLinkedList<TLinkableWrapper<AlternatingOrderElement>> optionalAlternatingOrderElements =
                new TLinkedList<TLinkableWrapper<AlternatingOrderElement>>();
        TLinkedList<TLinkableWrapper<LeftBeforeRight>> fixedOrderElements =
                new TLinkedList<TLinkableWrapper<LeftBeforeRight>>();
        TLinkableWrapper<SingleSyncActionBlock>[] blockArray =  syncActionBlocks.toUnlinkedArray(new TLinkableWrapper[0]);
        for (int outerIndex = 0; outerIndex < blockArray.length; outerIndex++) {
            for (int innerIndex = outerIndex + 1; innerIndex < blockArray.length; innerIndex++) {
                blockArray[outerIndex].element.createOrder(blockArray[innerIndex].element,fixedOrderElements,optionalAlternatingOrderElements);
            }
        }
        return  new FixedAndAlternatingOrder(fixedOrderElements.toUnlinkedArray(new TLinkableWrapper[0]),
                optionalAlternatingOrderElements.toUnlinkedArray(new TLinkableWrapper[0]));
    }

    private int[] calculateLength(TLinkedList<TLinkableWrapper<SyncActionAndPosition>> actualRun) {
        int maxThreadIndex = 0;
        for (TLinkableWrapper<SyncActionAndPosition> linkable : actualRun) {
            maxThreadIndex = max(linkable.element.position.threadIndex, maxThreadIndex);
        }
        int[] length = new int[maxThreadIndex + 1];
        for (TLinkableWrapper<SyncActionAndPosition> linkable : actualRun) {
            int i = linkable.element.position.threadIndex;
            length[i] = max(linkable.element.position.positionInThread + 1, length[i]);
        }
        return length;
    }

}
