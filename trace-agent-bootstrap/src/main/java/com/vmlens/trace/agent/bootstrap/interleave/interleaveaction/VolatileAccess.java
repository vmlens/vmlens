package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction;

import com.vmlens.trace.agent.bootstrap.interleave.LeftBeforeRight;
import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertree.AlternativeOneOrder;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.ordertreebuilder.TreeBuilderNode;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.BuildAlternatingOrderContext;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.KeyToOperationCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.dependentoperation.DependentOperationAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.dependentoperation.VolatileOperation;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.volatileaccesskey.VolatileKey;

import java.util.Objects;

import static com.vmlens.trace.agent.bootstrap.MemoryAccessType.IS_READ;

public class VolatileAccess implements VolatileOperation, InterleaveAction {

    private final MethodIdByteCodePositionAndThreadIndex methodIdByteCodePositionAndThreadIndex;
    private final VolatileKey volatileAccessKey;
    private final int operation;

    public VolatileAccess(MethodIdByteCodePositionAndThreadIndex methodIdByteCodePositionAndThreadIndex,
                          VolatileKey volatileAccessKey,
                          int operation) {
        this.methodIdByteCodePositionAndThreadIndex = methodIdByteCodePositionAndThreadIndex;
        this.volatileAccessKey = volatileAccessKey;
        this.operation = operation;
    }

    public VolatileKey key() {
        return volatileAccessKey;
    }


    @Override
    public void addToKeyToOperationCollection(Position myPosition,
                                              ActiveLockCollection mapContainingStack,
                                              KeyToOperationCollection result) {
        result.addVolatile(volatileAccessKey,new DependentOperationAndPosition<>(myPosition,this));
    }

    @Override
    public int threadIndex() {
        return methodIdByteCodePositionAndThreadIndex.threadIndex();
    }

    @Override
    public int operation() {
        return operation;
    }


    @Override
    public String toString() {
        return "volatileAccess(" +
                 methodIdByteCodePositionAndThreadIndex.threadIndex() +
                "," + volatileAccessKey +
                "," + operation +
                ");";
    }

    @Override
    public boolean equalsNormalized(InterleaveAction other) {
        if(! (other instanceof VolatileAccess)) {
            return false;
        }
        VolatileAccess otherLock = (VolatileAccess) other;
        if(! methodIdByteCodePositionAndThreadIndex.equals(otherLock.methodIdByteCodePositionAndThreadIndex))  {
            return false;
        }

        if(operation != otherLock.operation)  {
            return false;
        }
        return volatileAccessKey.equalsNormalized(otherLock.volatileAccessKey);
    }

    @Override
    public TreeBuilderNode addToAlternatingOrder(Position myPosition,
                                                 Object otherObj,
                                                 BuildAlternatingOrderContext context,
                                                 TreeBuilderNode treeBuilderNode) {
        DependentOperationAndPosition<VolatileOperation> other = (DependentOperationAndPosition<VolatileOperation>) otherObj;
        // if at least one interleaveoperation is a write
        if( operation > IS_READ || other.element().operation()  > IS_READ ) {
            treeBuilderNode = treeBuilderNode.either(new AlternativeOneOrder(LeftBeforeRight.lbr(myPosition,other.position())),
                    new AlternativeOneOrder(LeftBeforeRight.lbr(other.position(),myPosition)));
        }
        return treeBuilderNode;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        VolatileAccess that = (VolatileAccess) object;
        return operation == that.operation && Objects.equals(methodIdByteCodePositionAndThreadIndex, that.methodIdByteCodePositionAndThreadIndex) && Objects.equals(volatileAccessKey, that.volatileAccessKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(),methodIdByteCodePositionAndThreadIndex, volatileAccessKey, operation);
    }
}
