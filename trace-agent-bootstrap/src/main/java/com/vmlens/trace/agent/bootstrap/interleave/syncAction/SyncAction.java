package com.vmlens.trace.agent.bootstrap.interleave.syncAction;


import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.syncActionImpl.BlockListCollection;

public interface SyncAction {
    void createBlock(Position position, boolean moreThanOneThread, BuildBlockListContext buildBlockListContext,
                     BlockListCollection blockListCollection);

}
