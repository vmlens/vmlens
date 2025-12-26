package com.vmlens.trace.agent.bootstrap.interleave.context;

/**
 * Holds the configuration parameter for the interleave module.
 * And the InterleaveMessageFactory
 */
public class InterleaveLoopContext {

    private final int maximumIterations;
    private final int maximumAlternatingOrders;
    private final int synchronizationActionsLoopThreshold;
    private final int unsynchronizedOperationsLoopThreshold;
    private final InterleaveLoopMessageFactory loopMessageFactory;

    public InterleaveLoopContext(int maximumIterations,
                                 int maximumAlternatingOrders,
                                 int synchronizationActionsLoopThreshold,
                                 int unsynchronizedOperationsLoopThreshold,
                                 InterleaveLoopMessageFactory loopMessageFactory) {
        this.maximumIterations = maximumIterations;
        this.maximumAlternatingOrders = maximumAlternatingOrders;
        this.synchronizationActionsLoopThreshold = synchronizationActionsLoopThreshold;
        this.unsynchronizedOperationsLoopThreshold = unsynchronizedOperationsLoopThreshold;
        this.loopMessageFactory = loopMessageFactory;
    }

    public int maximumIterations() {
        return maximumIterations;
    }

    public int maximumAlternatingOrders() {
        return maximumAlternatingOrders;
    }

    public int synchronizationActionsLoopThreshold() {
        return synchronizationActionsLoopThreshold;
    }

    public int unsynchronizedOperationsLoopThreshold() {
        return unsynchronizedOperationsLoopThreshold;
    }

    public void maximumAlternatingOrdersCapped(int actual) {
        loopMessageFactory.maximumAlternatingOrdersCapped(actual);
    }

    public void maximumIterationsReached() {
        loopMessageFactory.maximumIterationsReached();
    }
}
