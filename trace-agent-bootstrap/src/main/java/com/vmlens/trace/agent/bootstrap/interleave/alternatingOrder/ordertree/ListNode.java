package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

public class ListNode implements OrderTreeNode{

    // next can be null
    private final OrderTreeNode next;
    private final ListNodeAlternative firstAlternative;
    private final ListNodeAlternative secondAlternative;

    public ListNode(OrderTreeNode next,
                    ListNodeAlternative firstAlternative,
                    ListNodeAlternative secondAlternative) {
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
}
