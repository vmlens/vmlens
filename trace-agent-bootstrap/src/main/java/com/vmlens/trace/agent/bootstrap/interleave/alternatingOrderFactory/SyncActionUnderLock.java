package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrderFactory;

import com.vmlens.trace.agent.bootstrap.interleave.domain.SyncActionAndPosition;

/**
 *
 * contains a stack for the current monitors and a map for the current locks
 *
 */

class SyncActionUnderLock {

    private final SyncActionAndPosition syncActionAndPosition;

    public SyncActionUnderLock(SyncActionAndPosition syncActionAndPosition) {
        this.syncActionAndPosition = syncActionAndPosition;
    }

}
