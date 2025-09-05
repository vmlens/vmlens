package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.interleavetypes;

import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lockcontainer.Block;

public interface LockContainerVisitor {
    AddToAlternatingOrder visit(Block block);
}
