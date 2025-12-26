package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.cycle;

public class CycleAdapterForFirst extends CycleAdapter {


    public CycleAdapterForFirst(boolean expectedChoice, OrderCycle orderCycle) {
        super(expectedChoice, orderCycle);
    }

    @Override
    protected void activate() {
        orderCycle.activateFirst();
    }
}
