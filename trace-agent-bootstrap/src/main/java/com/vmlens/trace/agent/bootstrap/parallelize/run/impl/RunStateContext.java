package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRunWithoutCalculated;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;

public class RunStateContext {

    private final ThreadIndexAndThreadStateMap runContext;
    private final InterleaveRun interleaveRun;

    public RunStateContext(ThreadIndexAndThreadStateMap runContext,
                           InterleaveRun interleaveRun) {
        this.runContext = runContext;
        this.interleaveRun = interleaveRun;
    }

    public RunStateContext withoutCalculated() {
        return new RunStateContext(runContext,
                new InterleaveRunWithoutCalculated(interleaveRun.actualRun()));
    }

    public boolean isActive(int threadIndex) {
        return interleaveRun.isActive(threadIndex,runContext.getActiveThreadIndices());
    }

    public ThreadIndexAndThreadStateMap runContext() {
        return runContext;
    }

    public ThreadLocalWhenInTest createForStartedThread(Run run,
                                                        ThreadLocalForParallelize threadLocalForParallelize,
                                                        int newThreadIndex,
                                                        SendEvent sendEvent) {
        return runContext.createForStartedThread(run, threadLocalForParallelize, newThreadIndex, sendEvent);
    }

    public boolean isBlocked() {
        Integer activeThreadIndex = interleaveRun.activeThreadIndex();
        if(activeThreadIndex != null) {
            return runContext.isBlocked(activeThreadIndex);
        }
        return false;
    }

    public int getThreadIndexForNewTestThread() {
        return runContext.getThreadIndexForNewTestThread();
    }

    public ActualRun actualRun() {
        return interleaveRun.actualRun();
    }

    public InterleaveRun interleaveRun() {
        return interleaveRun;
    }

}
