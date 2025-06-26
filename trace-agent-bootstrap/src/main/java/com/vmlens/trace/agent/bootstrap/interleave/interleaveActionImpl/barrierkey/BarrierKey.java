package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey;

import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;

public abstract class BarrierKey {

    public boolean equalsNormalized(NormalizeContext normalizeContext, BarrierKey other) {
        if(getClass() != other.getClass()) {
            return false;
        }
        int myCode = normalizeContext.normalizeObjectHashCode(objecthashcode());
        int otherCode =   normalizeContext.normalizeObjectHashCode(other.objecthashcode());
        return myCode == otherCode;
    }

    public abstract long objecthashcode();
}
