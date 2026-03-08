package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder.ListBuilderNode;

/**
 *
 * Helper interface, used for double dispatch for barriers and lock container
 *
 */

public interface AddToAlternatingOrder {
    ListBuilderNode addToAlternatingOrder(BuildAlternatingOrderContext context, ListBuilderNode listBuilderNode);
}
