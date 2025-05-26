package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

public class NodeBuilderStart extends EitherListOrStartNodeBuilder {

    @Override
    public OrderTreeNode build() {
        if(next == null) {
            return null;
        }
        return next.build();
    }
}
