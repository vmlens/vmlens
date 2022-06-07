package com.vmlens.trace.agent.bootstrap.parallelize.runAndLoop;

import com.vmlens.api.AllInterleavings;
import com.vmlens.trace.agent.bootstrap.interleave.facade.InterleaveFacade;

/**
 * Represents one AllInterleavingsLoop.
 * ToDo thread safety
 */

public class AllInterleavingsLoop {
    private final AllInterleavings allInterleavings;
    private final SynchronizationWrapperForRunFactory synchronizationWrapperForRunFactory;
    private final int loopId;

    // If null the loop is terminated
    private volatile InterleaveFacade interleaveFacade;

    // if null the loop is not yet started
    private volatile SynchronizationWrapperForRun currentRun;


    public AllInterleavingsLoop(SynchronizationWrapperForRunFactory synchronizationWrapperForRunFactory,
                                AllInterleavings allInterleavings, int loopId) {
        this.synchronizationWrapperForRunFactory = synchronizationWrapperForRunFactory;
        this.allInterleavings = allInterleavings;
        this.loopId = loopId;
        interleaveFacade = new InterleaveFacade(synchronizationWrapperForRunFactory.getLogger());
    }

    public InterleaveFacade getInterleaveFacade() {
        return interleaveFacade;
    }

    public SynchronizationWrapperForRun advance() {
        close();
        if (interleaveFacade.advance()) {
            currentRun = synchronizationWrapperForRunFactory.create(this);
            return currentRun;
        }

        return null;
    }


    public void close() {
        if (currentRun != null) {
            currentRun.close();
        }
    }

    // ToDo beginThread

}
