package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.cycle;

public class CycleAdapterForSecond extends CycleAdapter  {

    public CycleAdapterForSecond(boolean expectedChoice, OrderCycle orderCycle) {
        super(expectedChoice, orderCycle);
    }

    @Override
    protected void activate() {
        orderCycle.activateSecond();
    }

}
