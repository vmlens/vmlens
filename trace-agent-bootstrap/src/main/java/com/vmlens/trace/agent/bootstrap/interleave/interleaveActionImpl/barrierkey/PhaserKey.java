package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.barrierkey;

public class PhaserKey extends BarrierKey {

    private final long objecthashcode;

    public PhaserKey(long objecthashcode) {
        this.objecthashcode = objecthashcode;
    }

    @Override
    public long objecthashcode() {
        return objecthashcode;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;

        PhaserKey phaserKey = (PhaserKey) object;
        return objecthashcode == phaserKey.objecthashcode;
    }

    @Override
    public int hashCode() {
        return Long.hashCode(objecthashcode);
    }
}
