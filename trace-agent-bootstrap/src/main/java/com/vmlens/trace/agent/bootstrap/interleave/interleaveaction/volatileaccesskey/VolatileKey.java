package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.volatileaccesskey;

public interface VolatileKey {

    boolean equalsNormalized(VolatileKey other);
    int normalizedHashCode();
}
