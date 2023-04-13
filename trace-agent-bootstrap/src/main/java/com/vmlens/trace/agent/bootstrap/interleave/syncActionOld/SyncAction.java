package com.vmlens.trace.agent.bootstrap.interleave.syncActionOld;


import com.vmlens.trace.agent.bootstrap.interleave.Position;

public interface SyncAction {
    void createBlock(Position position, boolean moreThanOneThread, DeadlockAndLockContext deadlockAndLockContext,
                     BlockListCollection blockListCollection);

}
