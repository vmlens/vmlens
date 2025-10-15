package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContext;

public class OrderTreeBuilderContext {

    private final InterleaveLoopContext interleaveLoopContext;
    private int eitherBlockCount = 0;

    public OrderTreeBuilderContext(InterleaveLoopContext interleaveLoopContext) {
        this.interleaveLoopContext = interleaveLoopContext;
    }

    public void incrementEitherBlockCount() {
        eitherBlockCount++;
    }

    public boolean thresholdReached() {
        return eitherBlockCount > interleaveLoopContext.maximumAlternatingOrders();
    }
}
