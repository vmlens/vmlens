package com.vmlens.trace.agent.bootstrap.interleave.interleavetypes;

import com.vmlens.trace.agent.bootstrap.interleave.lockcontainer.Block;

public interface LockContainerVisitor {
    AddToAlternatingOrder visit(Block block);
}
