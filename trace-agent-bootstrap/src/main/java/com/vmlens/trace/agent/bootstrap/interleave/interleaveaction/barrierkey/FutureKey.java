package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey;

import java.util.Objects;

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
        return Objects.hash(getClass(),objecthashcode);
    }

    public long objectHashcode() {
        return objecthashcode;
    }

    @Override
    public int category() {
        return CATEGORY_FUTURE;
    }

    @Override
    public void accept(BarrierKeyVisitor visitor) {
        visitor.visit(this);
    }
}
