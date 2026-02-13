package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlist;

public interface OrderListIterator {

    boolean hasNext();

    /**
     * returns false when this order should no be processed,
     * e.g. because an element has only one alternative
     *
     */
    boolean advanceAndAddToOrder(CreateOrderContext context, boolean firstAlternative);

}
