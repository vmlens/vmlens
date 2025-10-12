package com.vmlens.trace.agent.bootstrap.interleave.interleaveaction;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.KeyToOperationCollection;
import com.vmlens.trace.agent.bootstrap.interleave.buildalternatingorder.lock.activelock.ActiveLockCollection;

import java.util.Objects;

public class LastInterleaveActionInThread implements InterleaveAction {

    private final int threadIndex;

    public LastInterleaveActionInThread(int threadIndex) {
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
    public void addToKeyToOperationCollection(Position myPosition, ActiveLockCollection mapContainingStack, KeyToOperationCollection result) {

    }

    @Override
    public boolean equals(Object object) {
        if (object == null || getClass() != object.getClass()) return false;
        LastInterleaveActionInThread that = (LastInterleaveActionInThread) object;
        return threadIndex == that.threadIndex;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getClass() , threadIndex);
    }
}
