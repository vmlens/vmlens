package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.element.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.LeftBeforeRightPair;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.OrderArrays;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.toArray;
import static com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper.wrap;

public class OrderArraysBuilder {

    private final TLinkedList<TLinkableWrapper<LeftBeforeRight>> fixedOrderList = new
            TLinkedList<>();
    private final TLinkedList<TLinkableWrapper<AlternatingOrderElement>> alternatingOrderList = new
            TLinkedList<>();
    private final TLinkedList<TLinkableWrapper<LeftBeforeRightPair>> optionalConstraintList = new
            TLinkedList<>();

    public void addAlternatingOrder(AlternatingOrderElement alternatingOrderElement) {
        alternatingOrderList.add(wrap(alternatingOrderElement));
    }

    public void addOptionalConstraint(LeftBeforeRightPair leftBeforeRightPair) {
        optionalConstraintList.add(wrap(leftBeforeRightPair));
    }

    public void addFixedOrder(LeftBeforeRight fixedOrder) {
        fixedOrderList.add(wrap(fixedOrder));
    }

    public OrderArrays build() {
        return new OrderArrays(toArray(LeftBeforeRight.class,fixedOrderList),
                toArray(AlternatingOrderElement.class,alternatingOrderList),
                toArray(LeftBeforeRightPair.class,optionalConstraintList));
    }

    // Visible for Test
    public TLinkedList<TLinkableWrapper<LeftBeforeRight>> fixedOrderList() {
        return fixedOrderList;
    }

    // Visible for Test
    public TLinkedList<TLinkableWrapper<AlternatingOrderElement>> alternatingOrderList() {
        return alternatingOrderList;
    }
}
