package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.cycle.ForEachApply;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.cycle.NodeWithCycles;

public class ListElementChoiceAlternative extends NodeWithCycles   {

    // only one is filled, the other is null
    private ListElementChoiceAlternative next;
    private ListElementChoice choice;

    public ListElementChoiceAlternative(OrderAlternative firstAlternative,
                                        OrderAlternative secondAlternative) {
        super(firstAlternative, secondAlternative);
    }

    public void setNext(ListElementChoiceAlternative next) {
        this.next = next;
    }

    public void setChoice(ListElementChoice choice) {
        this.choice = choice;
    }


    @Override
    public OrderTreeNode nextLeft() {
        if(next != null) {
            return next;
        }
        return choice.getNextForAlternative();
    }

    public boolean hasSameOrder(OrderTreeNode otherNode) {
        if(! (otherNode instanceof ListElementChoiceAlternative)) {
            return false;
        }
        ListElementChoiceAlternative other = (ListElementChoiceAlternative) otherNode;
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
        if(next != null) {
            return next;
        }
        return choice.getNextForAlternative();

    }
}
