package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.volatileaccesskey;

import java.util.Objects;

public class VolatileArrayKey  implements VolatileKey {

    private final int index;
    private final long objectHashCode;

    public VolatileArrayKey(int index, long objectHashCode) {
        this.index = index;
        this.objectHashCode = objectHashCode;
    }

    @Override
    public boolean equalsNormalized(VolatileKey other) {
        return other instanceof VolatileArrayKey;
    }


    @Override
    public String toString() {
        return "arrayAccess("  + index +  ","  + objectHashCode + "L)";
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        VolatileArrayKey that = (VolatileArrayKey) object;
        return  objectHashCode == that.objectHashCode;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(),objectHashCode);
    }
}
