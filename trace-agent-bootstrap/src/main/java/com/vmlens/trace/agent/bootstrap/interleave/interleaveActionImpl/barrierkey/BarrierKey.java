package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey;

import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;

public abstract class BarrierKey implements Comparable<BarrierKey> {

    protected static final int CATEGORY_FUTURE = 0;
    protected static final int CATEGORY_PHASER = 1;

    public boolean equalsNormalized(NormalizeContext normalizeContext, BarrierKey other) {
        if(getClass() != other.getClass()) {
            return false;
        }
        int myCode = normalizeContext.normalizeObjectHashCode(objecthashcode());
        int otherCode =   normalizeContext.normalizeObjectHashCode(other.objecthashcode());
        return myCode == otherCode;
    }

    @Override
    public int compareTo(BarrierKey other) {
        if(category() != other.category())  {
            return Integer.compare(category(),other.category());
        }

        return Long.compare(objecthashcode(), other.objecthashcode());
    }

    public abstract void accept(BarrierKeyVisitor visitor);
    public abstract long objecthashcode();
    protected abstract int category();
}
