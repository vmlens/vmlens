package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.callback.field.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.block.DependentBlock;
import com.vmlens.trace.agent.bootstrap.interleave.block.DependentBlockElement;
import com.vmlens.trace.agent.bootstrap.interleave.block.MapContainingStack;
import com.vmlens.trace.agent.bootstrap.interleave.block.MapOfBlocks;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;

public class VolatileFieldAccess implements InterleaveAction, DependentBlockElement {
    private static final int MIN_OPERATION = 3;
    private final int fieldId;
    private final int operation;
    private final int threadIndex;

    public VolatileFieldAccess(int fieldId, int operation, int threadIndex) {
        this.fieldId = fieldId;
        this.operation = operation;
        this.threadIndex = threadIndex;
    }

    @Override
    public void blockBuilderAdd(Position myPosition,
                                MapContainingStack mapContainingStack,
                                MapOfBlocks result) {
        DependentBlock dependentBlock = new DependentBlock(new ElementAndPosition<DependentBlockElement>(this, myPosition),
                new ElementAndPosition<DependentBlockElement>(this, myPosition));
        result.addDependent(new VolatileFieldAccessKey(fieldId), dependentBlock);
    }

    @Override
    public boolean startsAlternatingOrder(DependentBlockElement other) {
        VolatileFieldAccess otherVolatileFieldAccess = (VolatileFieldAccess) other;
        return (otherVolatileFieldAccess.operation | operation) >= MIN_OPERATION;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VolatileFieldAccess that = (VolatileFieldAccess) o;

        if (fieldId != that.fieldId) return false;
        if (operation != that.operation) return false;
        return threadIndex == that.threadIndex;
    }
    @Override
    public int hashCode() {
        int result = fieldId;
        result = 31 * result + operation;
        result = 31 * result + threadIndex;
        return result;
    }
    @Override
    public String toString() {
        return MemoryAccessType.asString(operation) +
                "(" + fieldId + ')';
    }
}
