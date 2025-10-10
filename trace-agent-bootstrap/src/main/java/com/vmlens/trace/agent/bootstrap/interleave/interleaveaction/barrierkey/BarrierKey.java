package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey;

public abstract class BarrierKey implements Comparable<BarrierKey> {

    protected static final int CATEGORY_FUTURE = 0;
    protected static final int CATEGORY_PHASER = 1;

    public boolean equalsNormalized(BarrierKey other) {
        return getClass() == other.getClass();
    }

    @Override
    public int compareTo(BarrierKey other) {
        if(category() != other.category())  {
            return Integer.compare(category(),other.category());
        }

        return Long.compare(objectHashcode(), other.objectHashcode());
    }

    public abstract void accept(BarrierKeyVisitor visitor);
    public abstract long objectHashcode();
    public abstract int category();
}
