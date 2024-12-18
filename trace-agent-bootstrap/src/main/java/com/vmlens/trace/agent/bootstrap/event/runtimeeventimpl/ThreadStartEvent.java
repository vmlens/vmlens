package com.vmlens.trace.agent.bootstrap.event.runtimeeventimpl;

import com.vmlens.trace.agent.bootstrap.event.InterleaveActionFactory;
import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.event.gen.ThreadStartEventGen;
import com.vmlens.trace.agent.bootstrap.interleave.interleaveActionImpl.ThreadStart;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveAction;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public class ThreadStartEvent extends ThreadStartEventGen implements RuntimeEvent, InterleaveActionFactory {

    private RunnableOrThreadWrapper runnableOrThreadWrapper;

    public ThreadStartEvent(RunnableOrThreadWrapper runnableOrThreadWrapper) {
        this.runnableOrThreadWrapper = runnableOrThreadWrapper;
    }

    public RunnableOrThreadWrapper runnableOrThreadWrapper() {
        return runnableOrThreadWrapper;
    }

    public void setRunnableOrThreadWrapperToNull() {
        this.runnableOrThreadWrapper = null;
    }

    public void setThreadIndex(int threadIndex) {
        this.threadIndex = threadIndex;
    }

    public void setMethodCounter(PerThreadCounter perThreadCounter) {
        this.methodCounter = perThreadCounter.methodCount();
    }

    public void setStartedThreadIndex(int startedThreadIndex) {
        this.startedThreadIndex = startedThreadIndex;
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
    public InterleaveAction create() {
        return new ThreadStart(threadIndex, startedThreadIndex);
    }
}
