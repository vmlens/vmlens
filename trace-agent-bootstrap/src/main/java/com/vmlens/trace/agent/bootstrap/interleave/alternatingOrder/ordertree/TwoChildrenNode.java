package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

public class TwoChildrenNode implements OrderTreeNode {

    private final OrderTreeNode firstAlternative;
    private final OrderTreeNode secondAlternative;

    public TwoChildrenNode(OrderTreeNode firstAlternative, OrderTreeNode secondAlternative) {
        this.firstAlternative = firstAlternative;
        this.secondAlternative = secondAlternative;
    }

    @Override
    public NextNodeAndProcessFlag nextAndAddToOrder(CreateOrderContext context, boolean firstAlternative) {
        if(firstAlternative) {
            return new NextNodeAndProcessFlag(this.firstAlternative,true);
        }
        return new NextNodeAndProcessFlag(this.secondAlternative,true);
    }

    // To test the builder
    public OrderTreeNode firstAlternative() {
        return firstAlternative;
    }

    // To test the builder
    public OrderTreeNode secondAlternative() {
        return secondAlternative;
    }

    @Override
    public OrderTreeNode nextLeft() {
        return firstAlternative;
    }

    @Override
    public boolean hasSameOrder(OrderTreeNode otherNode) {
        if(! (otherNode instanceof TwoChildrenNode)) {
            return false;
        }

        TwoChildrenNode other = (TwoChildrenNode) otherNode;

        if(firstAlternative.hasSameOrder(other.firstAlternative) && secondAlternative.hasSameOrder(other.secondAlternative)) {
            return true;
        }
        return firstAlternative.hasSameOrder(other.secondAlternative) && secondAlternative.hasSameOrder(other.firstAlternative);
    }
}
