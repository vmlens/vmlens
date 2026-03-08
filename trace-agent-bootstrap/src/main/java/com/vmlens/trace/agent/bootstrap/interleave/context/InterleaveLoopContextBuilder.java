package com.vmlens.trace.agent.bootstrap.interleave.context;

import com.vmlens.api.AllInterleavingsBuilder;
import com.vmlens.trace.agent.bootstrap.event.queue.QueueIn;

public class InterleaveLoopContextBuilder {

    private int maximumIterations = AllInterleavingsBuilder.MAXIMUM_ITERATIONS;
    private boolean traceInterleaveActions;

    public InterleaveLoopContextBuilder withMaximumIterations(int newValue) {
        maximumIterations = newValue;
        return this;
    }

    public InterleaveLoopContextBuilder withTraceInterleaveActions(boolean newValue) {
        traceInterleaveActions = newValue;
        return this;
    }



    public InterleaveLoopContext build(QueueIn queueIn,
                                       int loopId) {
        return new InterleaveLoopContext(maximumIterations,
                500,
                5000,
                traceInterleaveActions,
                new InterleaveLoopMessageFactoryImpl(queueIn,loopId));
    }

}
