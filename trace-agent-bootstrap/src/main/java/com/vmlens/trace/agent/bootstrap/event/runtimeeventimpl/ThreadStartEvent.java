package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.gen.ThreadStartEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.ExecuteBeforeEvent;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.NextStateBuilder;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.MethodIdByteCodePositionAndThreadIndex;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveaction.ThreadStart;
import com.vmlens.trace.agent.bootstrap.lock.ReadWriteLockMap;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;

public class ThreadStartEvent extends ThreadStartEventGen implements ExecuteBeforeEvent, WithInMethodIdPositionReadWriteLockMap {

    private ThreadWrapper threadWrapper;

    public ThreadStartEvent(ThreadWrapper threadWrapper, int eventType) {
        this.threadWrapper = threadWrapper;
        this.eventType = eventType;
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
        return new ThreadStart(new MethodIdByteCodePositionAndThreadIndex(methodId, bytecodePosition, threadIndex),
                startedThreadIndex);
    }

    @Override
    public void setInMethodIdAndPosition(int inMethodId, int position, ReadWriteLockMap readWriteLockMap) {
        this.methodId = inMethodId;
        this.bytecodePosition = position;
    }

    public void setInMethodIdAndPosition(int inMethodId, int position) {
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

    @Override
    public void addToBuilder(NextStateBuilder nextStateBuilder) {
        this.startedThreadIndex = nextStateBuilder.addThreadStarted(threadWrapper);
    }

    @Override
    public boolean startOrStopsThread() {
        return true;
    }
}
