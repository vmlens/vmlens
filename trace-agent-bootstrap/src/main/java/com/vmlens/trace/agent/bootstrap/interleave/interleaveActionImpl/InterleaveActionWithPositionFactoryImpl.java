package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.BlockBuilderAndCalculatedRunElementContainer;
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
        container.add(new ElementAndPosition<InterleaveAction>(interleaveAction,new Position(threadIndex,positionInThread)));
    }

    @Override
    public String toString() {
        return "InterleaveActionWithPositionFactoryImpl{" +
                "interleaveAction=" + interleaveAction +
                ", threadIndex=" + threadIndex +
                '}';
    }
}
