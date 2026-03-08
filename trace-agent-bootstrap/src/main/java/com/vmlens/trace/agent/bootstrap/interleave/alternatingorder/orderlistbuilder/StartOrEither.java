package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.OrderAlternative;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;


public abstract class StartOrEither implements ListBuilderNode {

    private final TLinkedList<TLinkableWrapper<NodeBuilder>> nodeBuilderList;

    public StartOrEither(TLinkedList<TLinkableWrapper<NodeBuilder>> nodeBuilderList) {
        this.nodeBuilderList = nodeBuilderList;
    }

    public Choice choice() {
        Choice choice =  new Choice(nodeBuilderList);
        nodeBuilderList.add(TLinkableWrapper.wrap(choice));
        return choice;

    }


    public Either either(OrderAlternative orderAlternativeA, OrderAlternative orderAlternativeB) {
        Either either =  new Either(nodeBuilderList,orderAlternativeA,orderAlternativeB);
        nodeBuilderList.add(TLinkableWrapper.wrap(either));
        return either;

    }

}
