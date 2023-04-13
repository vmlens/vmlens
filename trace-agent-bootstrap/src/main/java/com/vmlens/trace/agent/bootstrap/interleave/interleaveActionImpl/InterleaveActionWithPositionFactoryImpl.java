package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.block.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.block.InterleaveActionWithPosition;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.InterleaveActionWithPositionFactory;

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
    public InterleaveActionWithPosition create(int positionInThread) {
        return new InterleaveActionWithPosition(interleaveAction,new Position(threadIndex,positionInThread));
    }
}
