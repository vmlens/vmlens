package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTreeNode;

public class NodeBuilderStart extends AbstractNodeBuilder {

    @Override
    public OrderTreeNode build() {
        if(next == null) {
            return null;
        }
        return next.build();
    }
}
