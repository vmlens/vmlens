package com.vmlens.trace.agent.bootstrap.parallelize.actionImpl;

import com.vmlens.trace.agent.bootstrap.event.impl.ThreadJoinedEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ActionContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalDataWhenInTest;

public class ParallelizeActionForThreadJoin implements ParallelizeAction {
    private final long joinedThreadId;

    public ParallelizeActionForThreadJoin(long joinedThreadId) {
        this.joinedThreadId = joinedThreadId;
    }

    @Override
    public RunState nextState(ActionContext context, ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        return context.current();
    }

    @Override
    public void addInterleaveActionAndOrEvent(ActionContext context, ThreadLocalDataWhenInTest threadLocalDataWhenInTest) {
        context.afterInterleaveActionWithPositionFactory(
                new ThreadJoinedEvent(), threadLocalDataWhenInTest);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ParallelizeActionForThreadJoin that = (ParallelizeActionForThreadJoin) o;

        return joinedThreadId == that.joinedThreadId;
    }

    @Override
    public int hashCode() {
        return (int) (joinedThreadId ^ (joinedThreadId >>> 32));
    }

    @Override
    public String toString() {
        return "ThreadJoin{" +
                "joinedThreadId=" + joinedThreadId +
                '}';
    }
}
