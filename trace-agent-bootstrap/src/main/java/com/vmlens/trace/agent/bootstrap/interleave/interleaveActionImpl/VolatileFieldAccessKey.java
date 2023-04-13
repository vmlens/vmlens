package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.block.BlockBuilderKey;
import com.vmlens.trace.agent.bootstrap.interleave.block.BlockKey;

public class VolatileFieldAccessKey implements BlockKey, BlockBuilderKey {
    private final int fieldId;

    public VolatileFieldAccessKey(int fieldId) {
        this.fieldId = fieldId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VolatileFieldAccessKey that = (VolatileFieldAccessKey) o;

        return fieldId == that.fieldId;
    }

    @Override
    public int hashCode() {
        return fieldId;
    }
}
