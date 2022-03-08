package com.vmlens.trace.agent.bootstrap.interleave.blockFactory;

import com.vmlens.trace.agent.bootstrap.interleave.Position;

/**
 * For most operations this is a sync actions.
 */
public  class BlockFactory {

    public final Position position;
    private final int activeThreadCount;
    private final SyncAction syncAction;


    public BlockFactory(Position position, int activeThreadCount, SyncAction syncAction) {
        this.position = position;
        this.activeThreadCount = activeThreadCount;
        this.syncAction = syncAction;
    }

    public void createBlock(BuildBlockListContext buildBlockListContext, BlockListCollection blockListCollection) {
        syncAction.createBlock(position,activeThreadCount,buildBlockListContext,blockListCollection);
    }

    


}
