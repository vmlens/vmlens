package com.vmlens.trace.agent.bootstrap.eventtype;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier.Barrier;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier.BarrierWait;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey.BarrierKey;

public class BarrierTypeWait implements BarrierType {

    public static final BarrierType SINGLETON = new BarrierTypeWait();


    private BarrierTypeWait() {

    }

    @Override
    public Integer asWaitingThreadIndex(int threadIndex) {
        return threadIndex;
    }

    @Override
    public Barrier create(int threadIndex, BarrierKey key) {
        return new BarrierWait(threadIndex,key);
    }
}
