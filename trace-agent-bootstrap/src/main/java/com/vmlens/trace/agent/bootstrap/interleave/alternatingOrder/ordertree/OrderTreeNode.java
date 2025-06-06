package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

public interface OrderTreeNode {

    NextNodeAndProcessFlag nextAndAddToOrder(CreateOrderContext context, boolean firstAlternative);

}
