package com.vmlens.trace.agent.bootstrap.barrierkeytype;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.BarrierKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.FutureKey;

public class BarrierKeyTypeFuture implements BarrierKeyType {

    public static final BarrierKeyType SINGLETON = new BarrierKeyTypeFuture();

    private BarrierKeyTypeFuture() {
    }

    @Override
    public BarrierKey create(long objectHashCode) {
        return new FutureKey(objectHashCode);
    }
}
