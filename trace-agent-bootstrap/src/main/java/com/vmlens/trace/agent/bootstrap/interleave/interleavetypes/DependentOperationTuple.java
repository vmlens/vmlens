package com.vmlens.trace.agent.bootstrap.interleave.interleavetypes;

import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext.BuildAlternatingOrderContext;

public interface DependentOperationTuple {
    void addToAlternatingOrder(BuildAlternatingOrderContext context);
}
