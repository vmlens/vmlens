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
}
