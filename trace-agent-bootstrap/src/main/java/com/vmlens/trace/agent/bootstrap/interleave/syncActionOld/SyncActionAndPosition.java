package com.vmlens.trace.agent.bootstrap.interleave.syncActionOld;

import com.vmlens.trace.agent.bootstrap.interleave.Position;

/**
 * For most operations this is a sync actions.
 */
public class SyncActionAndPosition {

    public final Position position;
    private final boolean moreThanOneThread;
    private final SyncAction syncAction;


    public SyncActionAndPosition(Position position, boolean moreThanOneThread, SyncAction syncAction) {
        this.position = position;
        this.moreThanOneThread = moreThanOneThread;
        this.syncAction = syncAction;
    }

    public void createBlock(DeadlockAndLockContext deadlockAndLockContext, BlockListCollection blockListCollection) {
        syncAction.createBlock(position, moreThanOneThread, deadlockAndLockContext, blockListCollection);
    }

    


}
