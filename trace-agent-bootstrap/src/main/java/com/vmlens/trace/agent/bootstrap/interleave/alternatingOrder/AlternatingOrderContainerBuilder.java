package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.ThreadIdToElementList;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRunElement;
import gnu.trove.list.linked.TLinkedList;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
public class AlternatingOrderContainerBuilder {

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

    public AlternatingOrderContainer build(ThreadIdToElementList<CalculatedRunElement> actualRun) {
        AlternatingOrderElement[]
                alternatingOrderArray = new  AlternatingOrderElement[alternatingOrderList.size()];
        int index = 0;
        for(TLinkableWrapper<AlternatingOrderElement> elem : alternatingOrderList) {
            alternatingOrderArray[index] = elem.element;
            index++;
        }

        LeftBeforeRight[]
                fixedOrderArray = new LeftBeforeRight[fixedOrderList.size()];
        index = 0;
        for(TLinkableWrapper<LeftBeforeRight> elem : fixedOrderList) {
            fixedOrderArray[index] = elem.element;
            index++;
        }

        return new AlternatingOrderContainer(fixedOrderArray,alternatingOrderArray, actualRun);
    }
}
