package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey;

public class PhaserKey extends BarrierKey {

    private final long objecthashcode;

    public PhaserKey(long objecthashcode) {
        this.objecthashcode = objecthashcode;
    }

    @Override
    public long objectHashcode() {
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

    @Override
    public int category() {
        return CATEGORY_PHASER;
    }

    @Override
    public void accept(BarrierKeyVisitor visitor) {
        visitor.visit(this);
    }
}
