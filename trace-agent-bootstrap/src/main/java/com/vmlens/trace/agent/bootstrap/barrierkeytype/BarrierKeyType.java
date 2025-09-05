package com.vmlens.trace.agent.bootstrap.barrierkeytype;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.BarrierKey;

public interface BarrierKeyType {

    BarrierKey create(long objectHashCode);

}
