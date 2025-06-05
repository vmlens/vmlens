package com.vmlens.trace.agent.bootstrap.interleave.interleavetypes;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier.BarrierNotify;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier.BarrierWait;

public interface BarrierOperationVisitor {

    DependentOperationTuple visit(Position myPosition, BarrierNotify other, Position otherPosition);
    DependentOperationTuple visit(Position myPosition, BarrierWait other, Position otherPosition);

}
