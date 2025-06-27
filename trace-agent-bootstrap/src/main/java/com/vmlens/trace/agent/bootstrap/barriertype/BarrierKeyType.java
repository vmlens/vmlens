package com.vmlens.trace.agent.bootstrap.barriertype;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey.BarrierKey;

public interface BarrierKeyType {

    BarrierKey create(long objectHashCode);

}
