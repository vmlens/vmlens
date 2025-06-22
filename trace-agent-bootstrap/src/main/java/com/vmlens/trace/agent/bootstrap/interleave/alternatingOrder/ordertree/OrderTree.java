package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;

public class OrderTree {

    // can be null, when only fixed orders exist
    private final OrderTreeNode start;
    private final int size;

    public OrderTree(OrderTreeNode start) {
        this.start = start;
        this.size = calculateSize(start);
    }

    private static int calculateSize(OrderTreeNode start) {
        int size = 0;
        OrderTreeNode current = start;
        while(current != null) {
            size++;
            current = current.nextLeft();
        }
        return size;
    }

    public OrderTreeIterator iterator() {
        return new OrderTreeIterator(start);
    }

    public int size() {
        return size;
    }

    // To test the builder
    public OrderTreeNode start() {
        return start;
    }

}
