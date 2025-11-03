package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock;

import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.AddToAlternatingOrder;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.lockcontainer.Block;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.lockcontainer.SingleOperation;

public interface LockContainerVisitor {
    AddToAlternatingOrder visit(Block block);

    AddToAlternatingOrder visit(SingleOperation singleOperation);
}
