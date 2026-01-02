package com.vmlens.trace.agent.bootstrap.interleave.context;

/**
 * Holds the configuration parameter for the interleave module.
 * And the InterleaveMessageFactory
 */
public class InterleaveLoopContext {

    private final int maximumIterations;
    private final int removeCycleThreshold;
    private final int synchronizationActionsLoopThreshold;
    private final int unsynchronizedOperationsLoopThreshold;
    private final InterleaveLoopMessageFactory loopMessageFactory;

    public InterleaveLoopContext(int maximumIterations,
                                 int removeCycleThreshold,
                                 int synchronizationActionsLoopThreshold,
                                 int unsynchronizedOperationsLoopThreshold,
                                 InterleaveLoopMessageFactory loopMessageFactory) {
        this.maximumIterations = maximumIterations;
        this.removeCycleThreshold = removeCycleThreshold;
        this.synchronizationActionsLoopThreshold = synchronizationActionsLoopThreshold;
        this.unsynchronizedOperationsLoopThreshold = unsynchronizedOperationsLoopThreshold;
        this.loopMessageFactory = loopMessageFactory;
    }

    public int maximumIterations() {
        return maximumIterations;
    }

    public int removeCycleThreshold() {
        return removeCycleThreshold;
    }

    public int synchronizationActionsLoopThreshold() {
        return synchronizationActionsLoopThreshold;
    }

    public int unsynchronizedOperationsLoopThreshold() {
        return unsynchronizedOperationsLoopThreshold;
    }

    public void cyclesRemoved(int orderTreeLength) {
        loopMessageFactory.cyclesRemoved(orderTreeLength);
    }

    public void maximumIterationsReached(int orderTreeLength) {
        loopMessageFactory.maximumIterationsReached(orderTreeLength);
    }
}
