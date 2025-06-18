package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeNoOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderAlternative;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.OrderTreeNode;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.SingleChildNode;

public class EitherInChoiceAlternative implements ChoiceElement  {

    private final OrderAlternative orderAlternativeA;
    private final OrderAlternative orderAlternativeB;
    private EitherInChoiceAlternative next;
    private NodeBuilder last;

    public EitherInChoiceAlternative(OrderAlternative orderAlternativeA, OrderAlternative orderAlternativeB) {
        this.orderAlternativeA = orderAlternativeA;
        this.orderAlternativeB = orderAlternativeB;
    }

    public EitherInChoiceAlternative either(OrderAlternative orderAlternativeA, OrderAlternative orderAlternativeB) {
        EitherInChoiceAlternative temp = new EitherInChoiceAlternative(orderAlternativeA,orderAlternativeB);
        next = temp;
        return temp;
    }

    @Override
    public OrderTreeNode build() {
        OrderTreeNode nextNode = null;
        if(getNext() != null) {
            nextNode = getNext().build();
        } else if(last != null){
            nextNode = last.build();
        }
        return new SingleChildNode(nextNode,orderAlternativeA,orderAlternativeB);
    }

    @Override
    public ChoiceElement getNext() {
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
    public void setLast(NodeBuilder last) {
        if(next != null) {
            throw new RuntimeException("next != null");
        }
        this.last = last;
    }
}
