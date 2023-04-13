package com.vmlens.trace.agent.bootstrap.interleave.block;

import com.vmlens.trace.agent.bootstrap.interleave.WithPosition;
import com.vmlens.trace.agent.bootstrap.interleave.WithThreadIndex;

public interface BlockElement extends WithThreadIndex, WithPosition {
    boolean startsAlternatingOrder(BlockElement other);
    boolean startsFixedOrder(BlockElement other);
    InterleaveAction interleaveAction();
}
