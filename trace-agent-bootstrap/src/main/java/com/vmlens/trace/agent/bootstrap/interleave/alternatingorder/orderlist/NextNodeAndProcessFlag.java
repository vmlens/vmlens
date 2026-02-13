package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist;

public class NextNodeAndProcessFlag {

    private final OrderListNode next;
    private final boolean process;

    public NextNodeAndProcessFlag(OrderListNode next, boolean process) {
        this.next = next;
        this.process = process;
    }

    public OrderListNode next() {
        return next;
    }

    public boolean process() {
        return process;
    }
}
