package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTreeNode;

public interface NodeBuilder {

    OrderTreeNode build(OrderTreeBuilderContext orderTreeBuilderContext);
    NodeBuilder getNext();

}
