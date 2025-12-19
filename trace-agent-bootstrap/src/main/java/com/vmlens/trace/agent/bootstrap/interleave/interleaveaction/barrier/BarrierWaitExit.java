package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrier;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.AddToAlternatingOrder;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.KeyToOperationCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.dependentoperation.BarrierOperationVisitor;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.MethodIdByteCodePositionAndThreadIndex;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.barrierkey.BarrierKey;

import java.util.Objects;

public class BarrierWaitExit extends Barrier implements BarrierOperationVisitor {


    public BarrierWaitExit(MethodIdByteCodePositionAndThreadIndex methodIdByteCodePositionAndThreadIndex,
                           BarrierKey barrierKey) {
        super(methodIdByteCodePositionAndThreadIndex, barrierKey);
    }


    @Override
    public AddToAlternatingOrder visit(Position myPosition, BarrierNotify other, Position otherPosition) {
        return null;
    }

    @Override
    public AddToAlternatingOrder visit(Position myPosition, BarrierWaitEnter other, Position otherPosition) {
        return null;
    }

    @Override
    public AddToAlternatingOrder visit(Position myPosition, BarrierReadState barrierReadState, Position acceptingPosition) {
        return new ReadStateChangeStateTuple(new BarrierOperationAndPosition<>(acceptingPosition,barrierReadState), myPosition);
    }

    @Override
    public void addToKeyToOperationCollection(Position myPosition, ActiveLockCollection mapContainingStack, KeyToOperationCollection result) {
        // Barrier exit does not generate order relations
    }

    @Override
    public AddToAlternatingOrder accept(BarrierOperationVisitor visitor, Position visitorPosition, Position acceptingPosition) {
        return null;
    }

    @Override
    public boolean equalsNormalized(InterleaveAction other) {
        if(! (other instanceof BarrierWaitExit)) {
            return false;
        }
        BarrierWaitExit otherLock = (BarrierWaitExit) other;
        if(! methodIdByteCodePositionAndThreadIndex.equals(otherLock.methodIdByteCodePositionAndThreadIndex))  {
            return false;
        }
        return barrierKey.equalsNormalized(otherLock.barrierKey);
    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        BarrierWaitExit that = (BarrierWaitExit) object;
        return Objects.equals(methodIdByteCodePositionAndThreadIndex, that.methodIdByteCodePositionAndThreadIndex) && Objects.equals(barrierKey, that.barrierKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass(),methodIdByteCodePositionAndThreadIndex, barrierKey);
    }
}
