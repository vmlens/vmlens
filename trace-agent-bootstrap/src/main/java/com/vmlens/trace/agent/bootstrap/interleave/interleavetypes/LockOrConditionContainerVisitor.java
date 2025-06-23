package com.vmlens.trace.agent.bootstrap.interleave.interleavetypes;

import com.vmlens.trace.agent.bootstrap.interleave.lockorconditioncontainer.Block;

public interface LockOrConditionContainerVisitor {
    AddToAlternatingOrder visit(Block block);
}
