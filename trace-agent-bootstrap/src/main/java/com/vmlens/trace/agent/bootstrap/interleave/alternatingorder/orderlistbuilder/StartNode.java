package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder;

import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class StartNode extends StartOrEither {

    public StartNode(TLinkedList<TLinkableWrapper<NodeBuilder>> nodeBuilderList) {
        super(nodeBuilderList);
    }
}
