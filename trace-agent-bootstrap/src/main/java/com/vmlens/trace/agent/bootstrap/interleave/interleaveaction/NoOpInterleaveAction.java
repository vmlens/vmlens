package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.KeyToOperationCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.ActiveLockCollection;

import java.util.Objects;

public class NoOpInterleaveAction implements InterleaveAction {

    private final int threadIndex;

    public NoOpInterleaveAction(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    @Override
    public boolean equalsNormalized(InterleaveAction other) {
        return equals(other);
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
        NoOpInterleaveAction that = (NoOpInterleaveAction) object;
        return threadIndex == that.threadIndex;
    }

    @Override
    public int normalizedHashCode() {
        return Objects.hash(getClass(), threadIndex);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(threadIndex);
    }

    @Override
    public String toString() {
        return "noOp(" + threadIndex + ");";
    }
}
