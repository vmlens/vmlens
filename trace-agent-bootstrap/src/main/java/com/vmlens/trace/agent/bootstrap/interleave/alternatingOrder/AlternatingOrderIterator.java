package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class AlternatingOrderIterator {

    private final TLinkableWrapper<LeftBeforeRight>[] fixedOrder;
    private final TLinkableWrapper<AlternatingOrderElement>[] optionalAlternatingOrderElements;
    private final PermutationIterator permutationIterator;

    public AlternatingOrderIterator(TLinkableWrapper<LeftBeforeRight>[] fixedOrder,
                                    TLinkableWrapper<AlternatingOrderElement>[] optionalAlternatingOrderElements) {
        this.fixedOrder = fixedOrder;
        this.optionalAlternatingOrderElements = optionalAlternatingOrderElements;
        this.permutationIterator = new PermutationIterator(optionalAlternatingOrderElements.length);
    }

    TLinkedList<TLinkableWrapper<LeftBeforeRight>> next() {
        TLinkedList<TLinkableWrapper<LeftBeforeRight>>   order =
                new TLinkedList<TLinkableWrapper<LeftBeforeRight>>();
        for(int i = 0 ; i <  fixedOrder.length; i++) {
            order.add( new TLinkableWrapper(fixedOrder[i].element));
        }
        for (int i =0 ; i < optionalAlternatingOrderElements.length; i++) {
            order.add( new TLinkableWrapper( optionalAlternatingOrderElements[i].element.getOrder(permutationIterator.at(i))));
        }
        permutationIterator.advance();
        return order;
    }

    boolean hasNext() {
        return permutationIterator.hasNext();
    }

}
