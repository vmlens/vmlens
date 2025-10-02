package com.vmlens.trace.agent.bootstrap.interleave.context;

public class InterleaveLoopContextBuilder {

    private int maximumIterations = 20;
    private int maximumAlternatingOrders = 10;

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
