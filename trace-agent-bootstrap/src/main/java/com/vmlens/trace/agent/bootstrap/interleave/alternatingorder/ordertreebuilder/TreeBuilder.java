package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTree;

public class TreeBuilder {

    private final StartOrNext start = new StartOrNext();


    public StartOrNext start() {
        return start;
    }

    public OrderTree build() {
        return new OrderTree(start.build());
    }
}
