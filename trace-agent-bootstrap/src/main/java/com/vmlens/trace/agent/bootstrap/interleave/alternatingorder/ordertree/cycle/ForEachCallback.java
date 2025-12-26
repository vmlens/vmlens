package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.cycle;

public class ForEachCallback {

    private final OrderCycle[] orderCycleArray;

    public ForEachCallback(OrderCycle[] orderCycleArray) {
        this.orderCycleArray = orderCycleArray;
    }

    public void consume(NodeWithCycles nodeWithCycles) {
        nodeWithCycles.avoidCycles(orderCycleArray);
    }

}
