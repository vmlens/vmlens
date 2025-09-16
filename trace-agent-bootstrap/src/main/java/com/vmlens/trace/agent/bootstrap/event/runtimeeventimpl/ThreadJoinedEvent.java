package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.ThreadJoinedEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.ThreadJoin;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;

public class ThreadJoinedEvent extends ThreadJoinedEventGen implements InterleaveActionFactory,
        WithInMethodIdPositionReadWriteLockMap {

    private long joinedThreadId;

    public ThreadJoinedEvent(long joinedThreadId) {
        this.joinedThreadId = joinedThreadId;
    }


    public ThreadJoinedEvent() {
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

    public void setJoinedThreadIndex(int joinedThreadIndex) {
        this.joinedThreadIndex = joinedThreadIndex;
    }

    public void setBytecodePosition(int bytecodePosition) {
        this.bytecodePosition = bytecodePosition;
    }

    public void setMethodId(int methodId) {
        this.methodId = methodId;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
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

    @Override
    public int loopId() {
        return loopId;
    }

    @Override
    public int runId() {
        return runId;
    }

}
