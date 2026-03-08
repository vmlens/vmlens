package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder;

import com.vmlens.trace.agent.bootstrap.interleave.WithThreadIndex;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder.ListBuilderNode;

/**
 * this is either a block, e.g. start and end position
 * ore a single operation e.g. one position
 *
 */
public interface DependentOperationAndPositionOrContainer<OTHER> extends WithThreadIndex  {

    ListBuilderNode addToAlternatingOrder(OTHER other,
                                          BuildAlternatingOrderContext context,
                                          ListBuilderNode listBuilderNode);

}
