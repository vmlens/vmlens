package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier;

import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.dependentoperation.DependentOperation;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.BarrierKey;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.dependentoperation.BarrierOperation;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;

public interface Barrier extends BarrierOperation , DependentOperation, InterleaveAction {

    BarrierKey key();

}
