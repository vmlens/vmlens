package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.volatileaccesskey;

import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;


public interface VolatileKey {

    boolean equalsNormalized(NormalizeContext normalizeContext, VolatileKey other);


}
