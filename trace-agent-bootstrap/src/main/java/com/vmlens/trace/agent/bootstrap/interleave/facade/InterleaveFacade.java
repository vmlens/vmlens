package com.vmlens.trace.agent.bootstrap.interleave.facade;

import com.vmlens.trace.agent.bootstrap.Logger;
import com.vmlens.trace.agent.bootstrap.interleave.blockFactory.SyncAction;

/**
 * responsible for loop detection, second run
 */
public class InterleaveFacade {

    private final InterleaveContainer interleaveContainer;
    private final Logger logger;

    // For easier mocking
    public InterleaveFacade(InterleaveContainer interleaveContainer, Logger logger) {
        this.interleaveContainer = interleaveContainer;
        this.logger = logger;
    }

    public InterleaveFacade(Logger logger) {
        this(new InterleaveContainer(logger), logger);
    }

    //public int activeThreadIndex() {}

    public void afterSyncAction(SyncAction syncAction) {

    }

    public boolean advance() {
        return false;
    }
}
