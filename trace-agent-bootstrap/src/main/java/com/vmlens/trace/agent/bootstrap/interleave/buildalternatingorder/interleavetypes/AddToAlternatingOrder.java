package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.interleavetypes;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.BuildAlternatingOrderContext;

/**
 *
 * Helper interface, used for double dispatch for barriers and lock container
 *
 */

public interface AddToAlternatingOrder {
    TreeBuilderNode addToAlternatingOrder(BuildAlternatingOrderContext context, TreeBuilderNode treeBuilderNode);
}
