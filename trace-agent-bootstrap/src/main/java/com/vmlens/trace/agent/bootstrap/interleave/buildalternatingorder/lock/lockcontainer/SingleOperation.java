package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.lockcontainer;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.AddToAlternatingOrder;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.LockContainer;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.LockContainerVisitor;

public class SingleOperation extends LockContainer  {

    private final Position position;

    public SingleOperation(Position position) {
        this.position = position;
    }

    @Override
    public AddToAlternatingOrder accept(LockContainerVisitor visitor) {
        return visitor.visit(this);
    }


    @Override
    public int threadIndex() {
        return position.threadIndex();
    }

    @Override
    public AddToAlternatingOrder visit(Block block) {
        return new BlockSingleOperationTuple(block,this);
    }

    /*
     * To GetState Operation do not lead to an alternating order
     */
    @Override
    public AddToAlternatingOrder visit(SingleOperation singleOperation) {
        return null;
    }

    public Position position() {
        return position;
    }
}
