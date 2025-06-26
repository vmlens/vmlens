package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey;

public class FutureKey extends BarrierKey {

    private final long objecthashcode;

    public FutureKey(long objecthashcode) {
        this.objecthashcode = objecthashcode;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;

        FutureKey futureKey = (FutureKey) object;
        return objecthashcode == futureKey.objecthashcode;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(objecthashcode);
    }

    public long objecthashcode() {
        return objecthashcode;
    }
}
