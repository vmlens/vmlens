package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.dependentoperation;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.orderlistbuilder.ListBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.Position;

public interface DependentOperation {

    ListBuilderNode addToAlternatingOrder(Position myPosition,
                                          Object other,
                                          BuildAlternatingOrderContext context,
                                          ListBuilderNode listBuilderNode);

}
