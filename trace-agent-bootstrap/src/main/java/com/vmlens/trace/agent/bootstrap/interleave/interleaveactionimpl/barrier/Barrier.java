package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier;

import com.vmlens.trace.agent.bootstrap.interleave.dependentoperation.DependentOperation;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey.BarrierKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleavetypes.BarrierOperation;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public interface Barrier extends BarrierOperation , DependentOperation, InterleaveAction {

    BarrierKey key();

}
