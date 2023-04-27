package com.vmlens.trace.agent.bootstrap.interleave.run;

import com.vmlens.trace.agent.bootstrap.interleave.WithThreadIndex;

public interface InterleaveActionWithPositionFactory extends WithThreadIndex {
    void addToContainer(int positionInThread, BlockBuilderAndCalculatedRunElementContainer container);
}
