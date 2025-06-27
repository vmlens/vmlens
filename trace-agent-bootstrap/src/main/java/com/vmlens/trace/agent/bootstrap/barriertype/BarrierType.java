package com.vmlens.trace.agent.bootstrap.barriertype;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier.Barrier;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey.BarrierKey;

public interface BarrierType {

    Integer asWaitingThreadIndex(int threadIndex);
    Barrier create(int threadIndex, BarrierKey key);

}
