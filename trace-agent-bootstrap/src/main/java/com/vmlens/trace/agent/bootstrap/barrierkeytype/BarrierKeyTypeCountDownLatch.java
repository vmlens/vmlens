package com.vmlens.trace.agent.bootstrap.barrierkeytype;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.BarrierKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.CountDownLatchKey;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.FutureKey;

public class BarrierKeyTypeCountDownLatch implements BarrierKeyType {

    public static final BarrierKeyType SINGLETON = new BarrierKeyTypeCountDownLatch();

    private BarrierKeyTypeCountDownLatch() {
    }

    @Override
    public BarrierKey create(long objectHashCode) {
        return new CountDownLatchKey(objectHashCode);
    }
}