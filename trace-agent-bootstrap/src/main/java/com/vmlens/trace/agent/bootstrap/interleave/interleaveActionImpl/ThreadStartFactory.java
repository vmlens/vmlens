package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.BlockBuilderAndCalculatedRunElementContainer;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;

public class ThreadStartFactory  implements InterleaveActionWithPositionFactory  {
    private final int threadIndex;
    private final int startedThreadIndex;

    public ThreadStartFactory(int threadIndex, int startedThreadIndex) {
        this.threadIndex = threadIndex;
        this.startedThreadIndex = startedThreadIndex;
    }

    @Override
    public int threadIndex() {
        return 0;
    }
    @Override
    public void addToContainer(int positionInThread, BlockBuilderAndCalculatedRunElementContainer container) {
        container.add(new ElementAndPosition<InterleaveAction>(new ThreadStart(startedThreadIndex),
                new Position(threadIndex,positionInThread)));

    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThreadStartFactory that = (ThreadStartFactory) o;

        if (threadIndex != that.threadIndex) return false;
        return startedThreadIndex == that.startedThreadIndex;
    }
    @Override
    public int hashCode() {
        int result = threadIndex;
        result = 31 * result + startedThreadIndex;
        return result;
    }

    @Override
    public String toString() {
        return "ThreadStartFactory{" +
                "threadIndex=" + threadIndex +
                ", startedThreadIndex=" + startedThreadIndex +
                '}';
    }
}
