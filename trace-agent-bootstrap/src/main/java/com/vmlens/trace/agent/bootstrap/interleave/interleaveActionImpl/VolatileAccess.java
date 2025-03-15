package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.MemoryAccessType;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.element.AlternatingOrderElementStrategy;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.element.AlwaysEnabled;
import com.vmlens.trace.agent.bootstrap.interleave.block.dependent.DependentBlock;
import com.vmlens.trace.agent.bootstrap.interleave.block.dependent.DependentBlockElement;
import com.vmlens.trace.agent.bootstrap.interleave.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.block.MapOfBlocks;
import com.vmlens.trace.agent.bootstrap.interleave.deadlock.BlockingLockRelationBuilder;

public class VolatileAccess extends InterleaveActionForDependentBlock {
    private static final int MIN_OPERATION = 3;

    private final int threadIndex;
    private final int fieldId;
    private final int operation;


    public VolatileAccess(int threadIndex, int fieldId, int operation) {
        this.threadIndex = threadIndex;
        this.fieldId = fieldId;
        this.operation = operation;
    }

    @Override
    public AlternatingOrderElementStrategy alternatingOrderElementStrategy() {
        return new AlwaysEnabled();
    }

    @Override
    public void addToBlockingLockRelationBuilder(Position position, BlockingLockRelationBuilder builder) {
        // Nothing To do
    }

    @Override
    public void blockBuilderAdd(Position myPosition,
                                ActiveLockCollection mapContainingStack,
                                MapOfBlocks result) {
        DependentBlock dependentBlock = new DependentBlock(new ElementAndPosition<>(this, myPosition),
                new ElementAndPosition<>(this, myPosition));
        result.addDependent(new VolatileFieldAccessKey(fieldId), dependentBlock);
    }

    @Override
    protected Object blockKey() {
        return new VolatileFieldAccessKey(fieldId);
    }

    @Override
    public boolean startsAlternatingOrder(DependentBlockElement other) {
        VolatileAccess otherVolatileFieldAccess = (VolatileAccess) other;
        return (otherVolatileFieldAccess.operation | operation) >= MIN_OPERATION;
    }

    @Override
    public int threadIndex() {
        return threadIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VolatileAccess that = (VolatileAccess) o;

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
