package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.ListElement;

public class ListElementEither implements ListElement {

    private final OrderAlternative firstAlternative;
    private final OrderAlternative secondAlternative;
    // next can be null
    private OrderTreeNode next;

    public ListElementEither(OrderAlternative firstAlternative,
                             OrderAlternative secondAlternative) {
        this.firstAlternative = firstAlternative;
        this.secondAlternative = secondAlternative;
    }

    @Override
    public NextNodeAndProcessFlag nextAndAddToOrder(CreateOrderContext context, boolean takeFirstAlternative) {
        boolean processFlag;
        if(takeFirstAlternative) {
            processFlag = firstAlternative.process(context);
        } else {
            processFlag = secondAlternative.process(context);
        }
        return new NextNodeAndProcessFlag(next,processFlag);
    }

    @Override
    public OrderTreeNode nextLeft() {
        return next;
    }

    public void setNext(ListElement next) {
        this.next = next;
    }

    // to test the builder
    public OrderTreeNode next() {
        return next;
    }

    // to test the builder
    public OrderAlternative firstAlternative() {
        return firstAlternative;
    }

    // to test the builder
    public OrderAlternative secondAlternative() {
        return secondAlternative;
    }


    public boolean hasSameOrder(OrderTreeNode otherNode) {
        if(! (otherNode instanceof ListElementEither)) {
            return false;
        }

        ListElementEither other = (ListElementEither) otherNode;

        if(firstAlternative.equals(other.firstAlternative) && secondAlternative.equals(other.secondAlternative)) {
            return true;
        }
        return firstAlternative.equals(other.secondAlternative) && secondAlternative.equals(other.firstAlternative);
    }


}
