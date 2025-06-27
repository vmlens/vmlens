package com.vmlens.trace.agent.bootstrap.barriertype;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier.Barrier;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey.BarrierKey;

public class BarrierTypeWaitNotify implements BarrierType {

    public static final BarrierType SINGLETON = new BarrierTypeWaitNotify();

    private BarrierTypeWaitNotify() {

    }

    @Override
    public Integer asWaitingThreadIndex(int threadIndex) {
        return threadIndex;
    }

    @Override
    public Barrier create(int threadIndex, BarrierKey key) {
        return null;
    }

}
