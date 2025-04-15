package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.ThreadJoinedEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.NotThreadStartedInterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadJoin;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class ThreadJoinedEvent extends ThreadJoinedEventGen implements NotThreadStartedInterleaveActionFactory,
        WithInMethodIdAndPosition {

    private final long joinedThreadId;

    public ThreadJoinedEvent(long joinedThreadId) {
        this.joinedThreadId = joinedThreadId;
    }

    public void setThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    public void setMethodCounter(PerThreadCounter perThreadCounter) {
        this.methodCounter = perThreadCounter.methodCount();
    }
    public void setLoopId(int loopId) {
        this.loopId = loopId;
    }
    public void setRunId(int runId) {
        this.runId = runId;
    }
    public void setRunPosition(int runPosition) {
        this.runPosition = runPosition;
    }


    @Override
    public InterleaveAction create(CreateInterleaveActionContext context) {
        this.joinedThreadIndex = context.threadIndexForThreadId(joinedThreadId);
        return new ThreadJoin(threadIndex,joinedThreadIndex);
    }

    @Override
    public void setInMethodIdAndPosition(int inMethodId, int position, ReadWriteLockMap readWriteLockMap) {
        this.methodId = inMethodId;
        this.bytecodePosition = position;
    }

}
