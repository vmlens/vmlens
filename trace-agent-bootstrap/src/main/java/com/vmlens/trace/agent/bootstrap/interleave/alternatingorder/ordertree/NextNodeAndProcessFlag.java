package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

public class NextNodeAndProcessFlag {

    private final OrderTreeNode next;
    private final boolean process;

    public NextNodeAndProcessFlag(OrderTreeNode next, boolean process) {
        this.next = next;
        this.process = process;
    }

    public OrderTreeNode next() {
        return next;
    }

    public boolean process() {
        return process;
    }
}
