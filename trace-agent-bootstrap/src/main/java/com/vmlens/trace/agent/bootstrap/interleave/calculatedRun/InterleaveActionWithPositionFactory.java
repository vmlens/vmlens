package com.vmlens.trace.agent.bootstrap.interleave.calculatedRun;

import com.vmlens.trace.agent.bootstrap.interleave.WithThreadIndex;
import com.vmlens.trace.agent.bootstrap.interleave.block.InterleaveActionWithPosition;

public interface InterleaveActionWithPositionFactory extends WithThreadIndex {
    InterleaveActionWithPosition create(int positionInThread);
}
