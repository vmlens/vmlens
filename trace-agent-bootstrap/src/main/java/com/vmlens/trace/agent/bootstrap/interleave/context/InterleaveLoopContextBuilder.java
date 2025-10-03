package com.vmlens.trace.agent.bootstrap.interleave.context;

import com.vmlens.api.AllInterleavingsBuilder;

public class InterleaveLoopContextBuilder {

    private int maximumIterations = AllInterleavingsBuilder.MAXIMUM_ITERATIONS;
    private int maximumAlternatingOrders = AllInterleavingsBuilder.MAXIMUM_ALTERNATING_ORDERS;

    public InterleaveLoopContextBuilder withMaximumIterations(int newValue) {
        maximumIterations = newValue;
        return this;
    }

    public InterleaveLoopContextBuilder withMaximumAlternatingOrders(int newValue) {
        maximumAlternatingOrders = newValue;
        return this;
    }


    public InterleaveLoopContext build() {
        return new InterleaveLoopContext(maximumIterations, maximumAlternatingOrders, 500,
                5000 , new InterleaveLoopMessageFactoryImpl());
    }

}
