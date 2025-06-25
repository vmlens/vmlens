package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRunWithoutCalculated;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;
import gnu.trove.iterator.TIntIterator;
import gnu.trove.set.hash.TIntHashSet;

import static com.vmlens.trace.agent.bootstrap.exception.Message.TEST_BLOCKED_MESSAGE;

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

    public boolean isActive(int threadIndex,SendEvent sendEvent) {
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

    public boolean isBlocked(SendEvent sendEvent) {
        Integer activeThreadIndex = interleaveRun.activeThreadIndex(runContext.getActiveThreadIndices());
        if(activeThreadIndex != null) {
            ThreadState state = runContext.isBlocked(activeThreadIndex);
            switch (state) {
                case TERMINATED : {
                    return true;
                }
                case BLOCKED : {
                    if(interleaveRun.withCalculated()) {
                        sendEvent.sendMessage(TEST_BLOCKED_MESSAGE);
                    }
                    return true;
                }
                case ACTIVE : {
                    return false;
                }

            }
        }
        return false;
    }

    public TIntHashSet removeNotActive(TIntHashSet waitingThreadIndices) {
        TIntHashSet result = new TIntHashSet();
        TIntIterator iter = waitingThreadIndices.iterator();
        while(iter.hasNext()) {
            int index = iter.next();
            ThreadState state = runContext.isBlocked(index);
            switch (state) {
                case TERMINATED :
                case BLOCKED :
                    break;
                case ACTIVE:
                    result.add(index);
                    break;
            }
        }
        return result;
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
