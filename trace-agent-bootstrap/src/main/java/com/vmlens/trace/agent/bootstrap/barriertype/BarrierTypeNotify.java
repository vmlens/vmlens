package com.vmlens.trace.agent.bootstrap.barriertype;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier.Barrier;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrier.BarrierNotify;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey.BarrierKey;

public class BarrierTypeNotify implements BarrierType {

    public static final BarrierType SINGLETON = new BarrierTypeNotify();

    private BarrierTypeNotify() {

    }

    @Override
    public Integer asWaitingThreadIndex(int threadIndex) {
        return null;
    }

    @Override
    public Barrier create(int threadIndex, BarrierKey key) {
        return new BarrierNotify(threadIndex,key);
    }

    @Override
    public void accept(BarrierTypeVisitor visitor) {
        visitor.visit(this);
    }
}
