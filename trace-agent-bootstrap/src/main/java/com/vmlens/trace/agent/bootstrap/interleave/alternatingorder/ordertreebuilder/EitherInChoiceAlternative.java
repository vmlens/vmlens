package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.*;

public class EitherInChoiceAlternative implements ChoiceElement , EitherInChoice {

    private final OrderAlternative orderAlternativeA;
    private final OrderAlternative orderAlternativeB;
    private EitherInChoiceAlternative next;


    public EitherInChoiceAlternative(OrderAlternative orderAlternativeA, OrderAlternative orderAlternativeB) {
        this.orderAlternativeA = orderAlternativeA;
        this.orderAlternativeB = orderAlternativeB;
    }

    public EitherInChoice either(OrderAlternative orderAlternativeA, OrderAlternative orderAlternativeB) {
        EitherInChoiceAlternative temp = new EitherInChoiceAlternative(orderAlternativeA,orderAlternativeB);
        next = temp;
        return temp;
    }

    @Override
    public EitherInChoiceAlternative getNext() {
        return next;
    }

    @Override
    public void fill() {
        if(next != null) {
            throw new RuntimeException("next != null");
        }
        next = new EitherInChoiceAlternative(new AlternativeNoOrder(true),new AlternativeNoOrder(false));
    }

    @Override
    public ListElementChoiceAlternative build() {
        return new ListElementChoiceAlternative(orderAlternativeA,orderAlternativeB);
    }

}
