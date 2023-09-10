package com.vmlens.trace.agent.bootstrap.parallelize.actionImpl;

import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadJoinFactory;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ActionContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.TestThreadState;

public class ThreadJoin implements ParallelizeAction {
    private final long joinedThreadId;

    public ThreadJoin(long joinedThreadId) {
        this.joinedThreadId = joinedThreadId;
    }

    @Override
    public RunState nextState(ActionContext context, TestThreadState testThreadState) {
        return context.current();
    }

    @Override
    public void addInterleaveActionAndOrEvent(ActionContext context, TestThreadState testThreadState) {
        context.after(new ThreadJoinFactory(testThreadState.threadIndex(), context.threadIndexForId(joinedThreadId)));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ThreadJoin that = (ThreadJoin) o;

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
