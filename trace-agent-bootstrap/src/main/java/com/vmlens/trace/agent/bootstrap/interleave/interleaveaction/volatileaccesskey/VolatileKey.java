package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.volatileaccesskey;

import com.vmlens.trace.agent.bootstrap.interleave.loop.NormalizeContext;


public interface VolatileKey {

    boolean equalsNormalized(NormalizeContext normalizeContext, VolatileKey other);


}
