package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.run.BlockBuilderAndCalculatedRunElementContainer;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;

public class InterleaveActionWithPositionFactoryImpl implements InterleaveActionWithPositionFactory {
    private final InterleaveAction interleaveAction;
    private final int threadIndex;

    public InterleaveActionWithPositionFactoryImpl(InterleaveAction interleaveAction, int threadIndex) {
        this.interleaveAction = interleaveAction;
        this.threadIndex = threadIndex;
    }
    @Override
    public int threadIndex() {
        return threadIndex;
    }
    @Override
    public void addToContainer(int positionInThread, BlockBuilderAndCalculatedRunElementContainer container) {
        container.add(new ElementAndPosition<>(interleaveAction, new Position(threadIndex, positionInThread)));
    }

    @Override
    public String toString() {
        return interleaveAction +
                ", threadIndex=" + threadIndex +
                ')';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InterleaveActionWithPositionFactoryImpl that = (InterleaveActionWithPositionFactoryImpl) o;

        if (threadIndex != that.threadIndex) return false;
        return interleaveAction.equals(that.interleaveAction);
    }

    @Override
    public int hashCode() {
        int result = interleaveAction.hashCode();
        result = 31 * result + threadIndex;
        return result;
    }
}
