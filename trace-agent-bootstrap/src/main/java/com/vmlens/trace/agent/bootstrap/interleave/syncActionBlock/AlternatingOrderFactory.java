package com.vmlens.trace.agent.bootstrap.interleave.syncActionBlock;


import com.vmlens.trace.agent.bootstrap.Logger;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderContainer;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.FixedAndAlternatingOrder;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.BuildBlockListContext;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionImpl.BlockListCollection;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionImpl.SyncActionAndPosition;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

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
                createSyncActionBlocks(actualRun),
                calculateLength(actualRun));
    }

    // Fixme sortieren und per thread context
    // Fixme nach blockfactory verschieben als BlockFactoryCollection
    private FixedAndAlternatingOrder createSyncActionBlocks
    (TLinkedList<TLinkableWrapper<SyncActionAndPosition>> actualRun) {
        BlockListCollection blockListCollection = new BlockListCollection();
        BuildBlockListContext buildBlockListContext = new BuildBlockListContext();
        for (TLinkableWrapper<SyncActionAndPosition> linkable : actualRun) {
            linkable.element.createBlock(buildBlockListContext, blockListCollection);
        }
        return blockListCollection.createOrder(buildBlockListContext.create());
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
