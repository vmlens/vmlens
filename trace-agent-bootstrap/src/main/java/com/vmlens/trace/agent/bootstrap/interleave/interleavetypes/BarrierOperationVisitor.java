package com.vmlens.trace.agent.bootstrap.interleave.interleavetypes;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier.BarrierNotify;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier.BarrierWait;

public interface BarrierOperationVisitor {

    AddToAlternatingOrder visit(Position myPosition, BarrierNotify other, Position otherPosition);
    AddToAlternatingOrder visit(Position myPosition, BarrierWait other, Position otherPosition);

}
