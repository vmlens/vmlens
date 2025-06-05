package com.vmlens.trace.agent.bootstrap.interleave.interleaveoperation;

import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingordercontext.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.Position;

public interface DependentBlockOrOperation<BLOCK_OR_OPERATION> {

    void addToAlternatingOrder(Position myPosition, BLOCK_OR_OPERATION other, BuildAlternatingOrderContext context);

}
