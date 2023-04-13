package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.block.*;

public class VolatileFieldAccess implements InterleaveAction {
    private static final int MIN_OPERATION = 3;

    private final int fieldId;
    private final int operation;

    public VolatileFieldAccess(int fieldId, int operation) {
        this.fieldId = fieldId;
        this.operation = operation;
    }

    @Override
    public BlockBuilderKey blockBuilderKey() {
        return new VolatileFieldAccessKey(fieldId);
    }

    @Override
    public BlockKey blockKey() {
        return new VolatileFieldAccessKey(fieldId);
    }



    public boolean startsAlternatingOrder(InterleaveAction other) {
        VolatileFieldAccess otherVolatileFieldAccess = (VolatileFieldAccess) other;
        return (otherVolatileFieldAccess.operation | operation) >= MIN_OPERATION;
    }

    @Override
    public boolean startsFixedOrder(InterleaveAction interleaveAction, int otherThreadIndex) {
        return false;
    }
}
