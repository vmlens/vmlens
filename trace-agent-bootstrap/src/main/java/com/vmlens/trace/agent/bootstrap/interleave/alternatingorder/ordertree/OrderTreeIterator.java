package com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree;

public interface OrderTreeIterator {

    boolean hasNext();

    /**
     * returns false when this order should no be processed,
     * e.g. because an element has only one alternative
     *
     */
    boolean advanceAndAddToOrder(CreateOrderContext context, boolean firstAlternative);

}
