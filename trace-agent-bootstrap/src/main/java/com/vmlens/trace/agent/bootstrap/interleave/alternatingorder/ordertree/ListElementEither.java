package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.cycle.ForEachApply;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.cycle.NodeWithCycles;

public class ListElementEither extends NodeWithCycles implements ListElement {

    // next can be null
    private ListElement next;

    public ListElementEither(OrderAlternative firstAlternative,
                             OrderAlternative secondAlternative) {
        super(firstAlternative, secondAlternative);
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

    @Override
    protected ForEachApply nextForEach() {
        return next;
    }

    @Override
    protected OrderTreeNode nextForAddOrder() {
        return next;
    }

    @Override
    public ListElement getNextListElement() {
        return next;
    }

}
