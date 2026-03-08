package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey;

import java.util.Objects;

public class CountDownLatchKey extends BarrierKey {

    private final long objecthashcode;

    public CountDownLatchKey(long objecthashcode) {
        this.objecthashcode = objecthashcode;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;

        CountDownLatchKey futureKey = (CountDownLatchKey) object;
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
        return CATEGORY_COUNT_DOWN_LATCH;
    }

    @Override
    public void accept(BarrierKeyVisitor visitor) {
        visitor.visit(this);
    }
}
