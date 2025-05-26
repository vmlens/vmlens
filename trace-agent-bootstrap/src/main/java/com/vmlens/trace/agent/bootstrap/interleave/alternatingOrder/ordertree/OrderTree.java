package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;

public class OrderTree {

    // can be null, when only fixed orders exist
    private final OrderTreeNode start;
    private final LeftBeforeRight[] fixedOrder;

    public OrderTree(OrderTreeNode start, LeftBeforeRight[] fixedOrder) {
        this.start = start;
        this.fixedOrder = fixedOrder;
    }

    public OrderTreeIterator iterator() {
        return new OrderTreeIterator(start);
    }

    public LeftBeforeRight[] fixedOrder() {
        return fixedOrder;
    }
}
