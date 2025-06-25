package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.gen.BarrierEventGen;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.CreateInterleaveActionContext;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.LockExitOrWaitEvent;
import com.vmlens.trace.agent.bootstrap.eventtype.BarrierType;
import com.vmlens.trace.agent.bootstrap.eventtype.BarrierTypeCollection;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public class BarrierEvent extends BarrierEventGen implements LockExitOrWaitEvent {

    private final BarrierType barrierTypeObject;

    public BarrierEvent(BarrierType barrierType) {
        this.barrierTypeObject = barrierType;
        this.barrierType = BarrierTypeCollection.SINGLETON.toId(barrierType);
    }

    public void setThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    public void setMethodCounter(int methodCounter) {
        this.methodCounter = methodCounter;
    }

    public void setObjectHashCode(long objectHashCode) {
        this.objectHashCode = objectHashCode;
    }

    public void setBarrierType(int barrierType) {
        this.barrierType = barrierType;
    }

    public void setBytecodePosition(int bytecodePosition) {
        this.bytecodePosition = bytecodePosition;
    }

    public void setMethodId(int methodId) {
        this.methodId = methodId;
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
    public Integer waitingThreadIndex() {
        return barrierTypeObject.asWaitingThreadIndex(threadIndex);
    }

    @Override
    public void setStartedThreadIndex(int startedThreadIndex) {

    }

    @Override
    public void after(InterleaveRun interleaveRun, CreateInterleaveActionContext context, ThreadLocalWhenInTestForParallelize threadLocalWhenInTestForParallelize, SendEvent sendEvent) {

    }
}
