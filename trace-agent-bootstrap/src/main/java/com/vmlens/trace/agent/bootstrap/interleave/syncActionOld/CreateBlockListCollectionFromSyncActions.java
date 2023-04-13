package com.vmlens.trace.agent.bootstrap.interleave.syncActionOld;

import com.vmlens.trace.agent.bootstrap.Logger;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class CreateBlockListCollectionFromSyncActions {

    private final Logger logger;

    public CreateBlockListCollectionFromSyncActions(Logger logger) {
        this.logger = logger;
    }

    public BlockListCollection create(TLinkedList<TLinkableWrapper<SyncActionAndPosition>> actualRun) {
        BlockListCollection blockListCollection = new BlockListCollection();
        DeadlockAndLockContext deadlockAndLockContext = new DeadlockAndLockContext();
        for (TLinkableWrapper<SyncActionAndPosition> linkable : actualRun) {
            linkable.element.createBlock(deadlockAndLockContext, blockListCollection);
        }
        return blockListCollection;
    }

}
