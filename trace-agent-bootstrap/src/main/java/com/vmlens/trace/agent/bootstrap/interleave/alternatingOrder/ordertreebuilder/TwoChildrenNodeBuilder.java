package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.TwoChildrenNode;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTreeNode;

public class TwoChildrenNodeBuilder extends AbstractNodeBuilder {

    private final SingleChildNodeBuilder firstAlternative;
    private final SingleChildNodeBuilder secondAlternative;


    public TwoChildrenNodeBuilder(SingleChildNodeBuilder firstAlternative, SingleChildNodeBuilder secondAlternative) {
        this.firstAlternative = firstAlternative;
        this.secondAlternative = secondAlternative;
    }

    @Override
    public OrderTreeNode build() {
        // here we join the two alternatives again
        firstAlternative.next = next;
        secondAlternative.next = next;

        return new TwoChildrenNode(firstAlternative.build(),secondAlternative.build());
    }
}
