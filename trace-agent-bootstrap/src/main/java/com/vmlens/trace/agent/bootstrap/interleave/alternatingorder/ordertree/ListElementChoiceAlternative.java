package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

public class ListElementChoiceAlternative  implements OrderTreeNode {

    private final OrderAlternative firstAlternative;
    private final OrderAlternative secondAlternative;


    // only one is filled, the other is null
    private ListElementChoiceAlternative next;
    private ListElementChoice choice;

    public ListElementChoiceAlternative(OrderAlternative firstAlternative,
                                        OrderAlternative secondAlternative) {
        this.firstAlternative = firstAlternative;
        this.secondAlternative = secondAlternative;
    }

    public void setNext(ListElementChoiceAlternative next) {
        this.next = next;
    }

    public void setChoice(ListElementChoice choice) {
        this.choice = choice;
    }

    @Override
    public NextNodeAndProcessFlag nextAndAddToOrder(CreateOrderContext context, boolean takeFirstAlternative) {
        boolean processFlag;
        if(takeFirstAlternative) {
            processFlag = firstAlternative.process(context);
        } else {
            processFlag = secondAlternative.process(context);
        }
        if(next != null) {
            return new NextNodeAndProcessFlag(next,processFlag);
        }
        return new NextNodeAndProcessFlag(choice.getNextForAlternative(),processFlag);
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

}
