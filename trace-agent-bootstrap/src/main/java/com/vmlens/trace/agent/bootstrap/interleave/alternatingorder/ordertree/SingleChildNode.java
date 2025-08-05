package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

public class SingleChildNode implements OrderTreeNode {

    // next can be null
    private final OrderTreeNode next;
    private final OrderAlternative firstAlternative;
    private final OrderAlternative secondAlternative;

    public SingleChildNode(OrderTreeNode next,
                           OrderAlternative firstAlternative,
                           OrderAlternative secondAlternative) {
        this.next = next;
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
        if(! (otherNode instanceof SingleChildNode)) {
            return false;
        }

        SingleChildNode other = (SingleChildNode) otherNode;

        if(firstAlternative.equals(other.firstAlternative) && secondAlternative.equals(other.secondAlternative)) {
            return true;
        }
        return firstAlternative.equals(other.secondAlternative) && secondAlternative.equals(other.firstAlternative);
    }


}
