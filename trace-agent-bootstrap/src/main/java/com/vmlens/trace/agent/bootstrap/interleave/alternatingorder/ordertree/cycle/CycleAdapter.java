package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.cycle;

public abstract class CycleAdapter {

    private final boolean expectedChoice;
    protected final OrderCycle orderCycle;

    public CycleAdapter(boolean expectedChoice,
                        OrderCycle orderCycle) {
        this.expectedChoice = expectedChoice;
        this.orderCycle = orderCycle;
    }

    public void activate(boolean choice) {
        if(expectedChoice == choice) {
            activate();
        }
    }

    public  boolean isActivated() {
        return orderCycle.isActivated();
    }

    protected abstract void activate();

}
