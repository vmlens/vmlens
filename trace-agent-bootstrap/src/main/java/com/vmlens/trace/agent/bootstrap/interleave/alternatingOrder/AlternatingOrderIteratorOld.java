package com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRightOld;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class AlternatingOrderIteratorOld {

    private final TLinkableWrapper<LeftBeforeRightOld>[] fixedOrder;
    private final TLinkableWrapper<AlternatingOrderElementOld>[] optionalAlternatingOrderElements;
    private final PermutationIterator permutationIterator;

    public AlternatingOrderIteratorOld(TLinkableWrapper<LeftBeforeRightOld>[] fixedOrder,
                                       TLinkableWrapper<AlternatingOrderElementOld>[] optionalAlternatingOrderElements) {
        this.fixedOrder = fixedOrder;
        this.optionalAlternatingOrderElements = optionalAlternatingOrderElements;
        this.permutationIterator = new PermutationIterator(optionalAlternatingOrderElements.length);
    }

    TLinkedList<TLinkableWrapper<LeftBeforeRightOld>> next() {
        TLinkedList<TLinkableWrapper<LeftBeforeRightOld>>   order =
                new TLinkedList<TLinkableWrapper<LeftBeforeRightOld>>();
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
