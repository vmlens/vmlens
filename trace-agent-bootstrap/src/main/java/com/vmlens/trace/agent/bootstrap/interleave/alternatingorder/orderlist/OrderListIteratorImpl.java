package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist;

public class OrderListIteratorImpl implements OrderListIterator {

    private OrderListNode current;

    public OrderListIteratorImpl(OrderListNode current) {
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
