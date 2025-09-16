package com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.dependentoperation;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.AddToAlternatingOrder;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier.BarrierNotify;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier.BarrierWaitEnter;

public interface BarrierOperationVisitor {

    AddToAlternatingOrder visit(Position myPosition, BarrierNotify other, Position otherPosition);
    AddToAlternatingOrder visit(Position myPosition, BarrierWaitEnter other, Position otherPosition);

}
