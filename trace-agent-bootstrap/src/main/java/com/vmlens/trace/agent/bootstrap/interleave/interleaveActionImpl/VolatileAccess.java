package com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.element.AlternatingOrderElementStrategy;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.element.AlwaysEnabled;
import com.vmlens.trace.agent.bootstrap.interleave.block.dependent.DependentBlock;
import com.vmlens.trace.agent.bootstrap.interleave.block.dependent.DependentBlockElement;
import com.vmlens.trace.agent.bootstrap.interleave.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.block.MapOfBlocks;
import com.vmlens.trace.agent.bootstrap.interleave.deadlock.BlockingLockRelationBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveactionimpl.volatileaccesskey.VolatileKey;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.NormalizeContext;

import java.util.Objects;

import static com.vmlens.trace.agent.bootstrap.MemoryAccessType.IS_READ;

public class VolatileAccess extends InterleaveActionForDependentBlock {
    private final int threadIndex;
    private final VolatileKey volatileAccessKey;
    private final int operation;

    public VolatileAccess(int threadIndex, VolatileKey volatileAccessKey, int operation) {
        this.threadIndex = threadIndex;
        this.volatileAccessKey = volatileAccessKey;
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
        result.addDependent(volatileAccessKey, dependentBlock);
    }

    @Override
    protected Object blockKey() {
        return volatileAccessKey;
    }

    @Override
    public boolean startsAlternatingOrder(DependentBlockElement other) {
        VolatileAccess otherVolatileFieldAccess = (VolatileAccess) other;
        // if at least one interleaveoperation is a write
        return otherVolatileFieldAccess.operation > IS_READ ||  operation > IS_READ;
    }

    @Override
    public int threadIndex() {
        return threadIndex;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        VolatileAccess that = (VolatileAccess) o;
        return threadIndex == that.threadIndex &&
                operation == that.operation &&
                Objects.equals(volatileAccessKey, that.volatileAccessKey);
    }

    @Override
    public int hashCode() {
        int result = threadIndex;
        result = 31 * result + Objects.hashCode(volatileAccessKey);
        result = 31 * result + operation;
        return result;
    }

    @Override
    public String toString() {
        return "VolatileAccess{" +
                "threadIndex=" + threadIndex +
                ", volatileAccessKey=" + volatileAccessKey +
                ", interleaveoperation=" + operation +
                '}';
    }

    @Override
    public boolean equalsNormalized(NormalizeContext normalizeContext, InterleaveAction other) {
        if(! (other instanceof VolatileAccess)) {
            return false;
        }
        VolatileAccess otherLock = (VolatileAccess) other;
        if(threadIndex != otherLock.threadIndex)  {
            return false;
        }
        if(operation != otherLock.operation)  {
            return false;
        }
        return volatileAccessKey.equalsNormalized(normalizeContext,otherLock.volatileAccessKey);
    }
}
