package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.*;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public class VolatileFieldAccess implements InterleaveAction, DependentBlockElement {
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
    public void blockBuilderStart(Position myPosition, BlockContainer result) {
        DependentBlock dependentBlock = new DependentBlock(new ElementAndPosition<DependentBlockElement>(this,myPosition),
                new ElementAndPosition<DependentBlockElement>(this,myPosition));
        result.addDependent(new VolatileFieldAccessKey(fieldId), dependentBlock);
    }
    @Override
    public void blockBuilderAdd(Position myPosition, ElementAndPosition<BlockBuilder> next, BlockContainer result) {
        VolatileFieldAccess nextVolatileFieldAccess = (VolatileFieldAccess) next.element();
        DependentBlock dependentBlock = new DependentBlock(new ElementAndPosition<DependentBlockElement>(nextVolatileFieldAccess,myPosition),
                new ElementAndPosition<DependentBlockElement>(nextVolatileFieldAccess,myPosition));
        result.addDependent(new VolatileFieldAccessKey(nextVolatileFieldAccess.fieldId), dependentBlock);
    }
    @Override
    public boolean startsAlternatingOrder(DependentBlockElement other) {
        VolatileFieldAccess otherVolatileFieldAccess = (VolatileFieldAccess) other;
        return (otherVolatileFieldAccess.operation | operation) >= MIN_OPERATION;
    }

}
