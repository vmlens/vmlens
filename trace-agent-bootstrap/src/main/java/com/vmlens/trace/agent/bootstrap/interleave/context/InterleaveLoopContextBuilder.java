package com.vmlens.trace.agent.bootstrap.interleave.context;

import com.vmlens.api.AllInterleavingsBuilder;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;

public class InterleaveLoopContextBuilder {

    private int maximumIterations = AllInterleavingsBuilder.MAXIMUM_ITERATIONS;
    private int removeCycleThreshold = AllInterleavingsBuilder.REMOVE_CYCLE_THRESHOLD;

    public InterleaveLoopContextBuilder withMaximumIterations(int newValue) {
        maximumIterations = newValue;
        return this;
    }

    public InterleaveLoopContextBuilder withRemoveCycleThreshold(int newValue) {
        removeCycleThreshold = newValue;
        return this;
    }

    public InterleaveLoopContext build(QueueIn queueIn,
                                       int loopId) {
        return new InterleaveLoopContext(maximumIterations, removeCycleThreshold, 500,
                5000 , new InterleaveLoopMessageFactoryImpl(queueIn,loopId));
    }

}
