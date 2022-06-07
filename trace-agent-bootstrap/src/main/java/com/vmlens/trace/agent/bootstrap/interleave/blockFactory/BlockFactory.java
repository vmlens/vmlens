package com.vmlens.trace.agent.bootstrap.interleave.blockFactory;

import com.vmlens.trace.agent.bootstrap.interleave.Position;

/**
 * For most operations this is a sync actions.
 */
public  class BlockFactory {

    public final Position position;
    private final boolean moreThanOneThread;
    private final SyncAction syncAction;


    public BlockFactory(Position position, boolean moreThanOneThread, SyncAction syncAction) {
        this.position = position;
        this.moreThanOneThread = moreThanOneThread;
        this.syncAction = syncAction;
    }

    public void createBlock(BuildBlockListContext buildBlockListContext, BlockListCollection blockListCollection) {
        syncAction.createBlock(position, moreThanOneThread, buildBlockListContext, blockListCollection);
    }

    


}
