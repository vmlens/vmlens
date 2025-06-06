package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTree;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.toArray;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class OrderTreeBuilder {

    private final TLinkedList<TLinkableWrapper<LeftBeforeRight>> fixedOrder = new TLinkedList<>();
    private final NodeBuilderStart start = new NodeBuilderStart();

    public void addFixedOrder(LeftBeforeRight order) {
        fixedOrder.add(wrap(order));
    }

    public NodeBuilderStart start() {
        return start;
    }

    public OrderTree build() {
        return new OrderTree(start.build(),toArray(LeftBeforeRight.class,fixedOrder));
    }
}
