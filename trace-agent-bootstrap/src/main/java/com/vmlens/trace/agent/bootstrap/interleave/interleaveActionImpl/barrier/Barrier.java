package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveoperation.DependentBlockOrOperation;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveoperation.DependentOperationAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.interleavetypes.BarrierOperation;

public interface Barrier extends BarrierOperation , DependentBlockOrOperation<DependentOperationAndPosition<Barrier>> {
}
