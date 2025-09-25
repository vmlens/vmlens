package com.vmlens.trace.agent.bootstrap.interleave.buildinterleaveactionloop;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.KeyToOperationCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.ActiveLockCollection;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;

import java.util.Objects;

public class InterleaveActionGuineaPig implements InterleaveAction {

    private final int threadIndex;
    private final String value;

    public InterleaveActionGuineaPig(int threadIndex,
                                     String value) {
        this.threadIndex = threadIndex;
        this.value = value;
    }

    public static InterleaveActionGuineaPig action(int threadIndex, String value) {
        return new InterleaveActionGuineaPig(threadIndex,value);
    }

    @Override
    public boolean equalsNormalized(InterleaveAction other) {
        InterleaveActionGuineaPig otherGuineaPig = (InterleaveActionGuineaPig)  other;
        return equals(otherGuineaPig);
    }



    @Override
    public int threadIndex() {
        return threadIndex;
    }

    @Override
    public void addToKeyToOperationCollection(Position myPosition,
                                              ActiveLockCollection mapContainingStack,
                                              KeyToOperationCollection result) {

    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        InterleaveActionGuineaPig that = (InterleaveActionGuineaPig) object;
        return threadIndex == that.threadIndex && Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(threadIndex, value);
    }
}
