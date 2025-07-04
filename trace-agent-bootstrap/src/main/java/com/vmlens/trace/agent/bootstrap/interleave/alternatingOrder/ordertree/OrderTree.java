package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;


public class OrderTree {

    // can be null, when only fixed orders exist
    private final OrderTreeNode start;
    private final int length;

    public OrderTree(OrderTreeNode start) {
        this.start = start;
        this.length = calculateLength(start);
    }

    private static int calculateLength(OrderTreeNode start) {
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

    public int length() {
        return length;
    }

    // To test the builder
    public OrderTreeNode start() {
        return start;
    }

}
