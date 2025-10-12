package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTree;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;

public class TreeBuilder {

    private final StartOrNext start = new StartOrNext();

    public StartOrNext start() {
        return start;
    }

    public OrderTree build(InterleaveLoopContext interleaveLoopContext) {
        return new OrderTree(start.build(new OrderTreeBuilderContext(interleaveLoopContext)));
    }
}
