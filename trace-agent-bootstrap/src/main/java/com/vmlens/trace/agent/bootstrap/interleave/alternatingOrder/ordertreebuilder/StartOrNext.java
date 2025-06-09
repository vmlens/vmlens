package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTreeNode;

public class StartOrNext extends StartOrEither {

    @Override
    public OrderTreeNode build() {
        if(getNext() != null)  {
            return getNext().build();
        }
        return null;
    }
}
