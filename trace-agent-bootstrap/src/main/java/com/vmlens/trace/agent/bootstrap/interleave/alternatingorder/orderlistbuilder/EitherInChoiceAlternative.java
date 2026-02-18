package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist.*;
import com.vmlens.trace.agent.bootstrap.util.TLinkableWrapper;
import gnu.trove.list.linked.TLinkedList;

public class EitherInChoiceAlternative implements ChoiceEither  {

    private final TLinkedList<TLinkableWrapper<EitherInChoiceAlternative>> eitherList;
    private final OrderAlternative orderAlternativeA;
    private final OrderAlternative orderAlternativeB;


    public EitherInChoiceAlternative(TLinkedList<TLinkableWrapper<EitherInChoiceAlternative>> eitherList,
                                     OrderAlternative orderAlternativeA,
                                     OrderAlternative orderAlternativeB) {
        this.eitherList = eitherList;
        this.orderAlternativeA = orderAlternativeA;
        this.orderAlternativeB = orderAlternativeB;
    }

    public EitherInChoiceAlternative either(OrderAlternative orderAlternativeA, OrderAlternative orderAlternativeB) {
        EitherInChoiceAlternative temp = new EitherInChoiceAlternative(eitherList,orderAlternativeA,orderAlternativeB);
        eitherList.add(TLinkableWrapper.wrap(temp));
        return temp;
    }



    public void addToCombinedAlternatives(TLinkedList<TLinkableWrapper<AlternativeOneOrder>> combinedAlternatives , boolean first) {
        if(first) {
            orderAlternativeA.addToCombinedAlternatives(combinedAlternatives);
        } else {
            orderAlternativeB.addToCombinedAlternatives(combinedAlternatives);
        }

    }

}
