package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.AlternatingOrderElement;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.OrderArrays;
import com.vmlens.trace.agent.bootstrap.list.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;
public class OrderArraysBuilder {

    private final TLinkedList<TLinkableWrapper<LeftBeforeRight>> fixedOrderList = new
            TLinkedList<>();
    private final TLinkedList<TLinkableWrapper<AlternatingOrderElement>> alternatingOrderList = new
            TLinkedList<>();

    public void addAlternatingOrder(AlternatingOrderElement alternatingOrderElement) {
        alternatingOrderList.add(new TLinkableWrapper<>(alternatingOrderElement));
    }

    public void addFixedOrder(LeftBeforeRight fixedOrder) {
        fixedOrderList.add(new TLinkableWrapper(fixedOrder));
    }

    public OrderArrays build() {
        AlternatingOrderElement[]
                alternatingOrderArray = new  AlternatingOrderElement[alternatingOrderList.size()];
        int index = 0;
        for(TLinkableWrapper<AlternatingOrderElement> elem : alternatingOrderList) {
            alternatingOrderArray[index] = elem.element();
            index++;
        }

        LeftBeforeRight[]
                fixedOrderArray = new LeftBeforeRight[fixedOrderList.size()];
        index = 0;
        for (TLinkableWrapper<LeftBeforeRight> elem : fixedOrderList) {
            fixedOrderArray[index] = elem.element();
            index++;
        }

        return new OrderArrays(fixedOrderArray, alternatingOrderArray);
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
