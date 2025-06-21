package com.vmlens.trace.agent.bootstrap.interleave.interleaveoperation;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.Position;

public interface DependentBlockOrOperation<BLOCK_OR_OPERATION> {

    TreeBuilderNode addToAlternatingOrder(Position myPosition,
                                          BLOCK_OR_OPERATION other,
                                          BuildAlternatingOrderContext context,
                                          TreeBuilderNode treeBuilderNode);

}
