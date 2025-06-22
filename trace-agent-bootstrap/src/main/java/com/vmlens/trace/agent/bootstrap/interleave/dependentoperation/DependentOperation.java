package com.vmlens.trace.agent.bootstrap.interleave.dependentoperation;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.Position;

public interface DependentOperation {

    TreeBuilderNode addToAlternatingOrder(Position myPosition,
                                          Object other,
                                          BuildAlternatingOrderContext context,
                                          TreeBuilderNode treeBuilderNode);

}
