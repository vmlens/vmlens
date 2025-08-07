package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

public class OrderTreeIteratorImpl implements OrderTreeIterator {

    private OrderTreeNode current;

    public OrderTreeIteratorImpl(OrderTreeNode current) {
        this.current = current;
    }

    public boolean hasNext() {
        return current != null;
    }

    /**
     * returns false when this order should no be processed,
     * e.g. because an element has only one alternative
     *
     */
    public boolean advanceAndAddToOrder(CreateOrderContext context, boolean firstAlternative) {
        NextNodeAndProcessFlag result = current.nextAndAddToOrder(context,firstAlternative);
        current = result.next();
        return result.process();
    }

}
