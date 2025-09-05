package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.volatileaccesskey;

import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;


public interface VolatileKey {

    boolean equalsNormalized(NormalizeContext normalizeContext, VolatileKey other);


}
