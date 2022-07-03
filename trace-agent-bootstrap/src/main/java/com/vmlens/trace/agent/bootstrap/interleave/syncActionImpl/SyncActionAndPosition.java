package com.vmlens.trace.agent.bootstrap.interleave.syncActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.BuildBlockListContext;
import com.vmlens.trace.agent.bootstrap.interleave.syncAction.SyncAction;

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

    public void createBlock(BuildBlockListContext buildBlockListContext, BlockListCollection blockListCollection) {
        syncAction.createBlock(position, moreThanOneThread, buildBlockListContext, blockListCollection);
    }

    


}
