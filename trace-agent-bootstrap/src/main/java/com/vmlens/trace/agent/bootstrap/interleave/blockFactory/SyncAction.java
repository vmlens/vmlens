package com.vmlens.trace.agent.bootstrap.interleave.blockFactory;


import com.vmlens.trace.agent.bootstrap.interleave.Position;

public interface SyncAction {
    void createBlock(Position position, boolean moreThanOneThread, BuildBlockListContext buildBlockListContext,
                     BlockListCollection blockListCollection);

}
