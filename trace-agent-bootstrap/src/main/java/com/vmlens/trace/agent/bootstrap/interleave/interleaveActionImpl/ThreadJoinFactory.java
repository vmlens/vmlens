package com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.Position;
import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.ElementAndPosition;
import com.vmlens.trace.agent.bootstrap.interleave.run.BlockBuilderAndCalculatedRunElementContainer;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveActionWithPositionFactory;

public class ThreadJoinFactory implements InterleaveActionWithPositionFactory {
    private final int threadIndex;
    private final int joinedThreadIndex;

    public ThreadJoinFactory(int threadIndex, int joinedThreadIndex) {
        this.threadIndex = threadIndex;
        this.joinedThreadIndex = joinedThreadIndex;
    }

    @Override
    public int threadIndex() {
        return threadIndex;
    }

    @Override
    public void addToContainer(int positionInThread, BlockBuilderAndCalculatedRunElementContainer container) {
        container.add(new ElementAndPosition<InterleaveAction>(new ThreadJoin(joinedThreadIndex),
                new Position(threadIndex, positionInThread)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThreadJoinFactory that = (ThreadJoinFactory) o;

        if (threadIndex != that.threadIndex) return false;
        return joinedThreadIndex == that.joinedThreadIndex;
    }

    @Override
    public int hashCode() {
        int result = threadIndex;
        result = 31 * result + joinedThreadIndex;
        return result;
    }

    @Override
    public String toString() {
        return "ThreadJoinFactory{" +
                "threadIndex=" + threadIndex +
                ", joinedThreadIndex=" + joinedThreadIndex +
                '}';
    }
}
