package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate;


import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.NewTaskContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.AfterContextForStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateAndResult;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;
import gnu.trove.set.hash.TIntHashSet;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate.ProcessAfter.process;

public class RunStateActive extends ProcessLockExitOrWaitTemplate implements RunState {

    private final RunStateContext runStateContext;
    private final int startedThreadIndex;

    public RunStateActive(RunStateContext runStateContext) {
        this.runStateContext = runStateContext;
        this.startedThreadIndex = -1;
    }

    public RunStateActive(RunStateContext runStateContext,
                          int startedThreadIndex) {
        this.runStateContext = runStateContext;
        this.startedThreadIndex = startedThreadIndex;
    }

    public static boolean calculateIsActive(RunStateContext runStateContext,
                                            ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,
                                            SendEvent sendEvent) {
        return runStateContext.isActive(threadLocalDataWhenInTest.threadIndex(),sendEvent);
    }


    @Override
    public boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,SendEvent sendEvent) {
       return calculateIsActive(runStateContext,threadLocalDataWhenInTest,sendEvent);
    }

    @Override
    public ActualRun actualRun() {
        return runStateContext.actualRun();
    }

    @Override
    public RunState after(AfterContextForStateMachine afterContext, SendEvent sendEvent) {
        process(afterContext, sendEvent, runStateContext, startedThreadIndex);
        return this;
    }

    @Override
    public RunState newTestTaskStarted(ThreadWrapper startedThread) {
        int threadIndexForNewTestTask = runStateContext.getThreadIndexForNewTestThread();
        return new RunStateNewThreadStarted(runStateContext, startedThread,threadIndexForNewTestTask);
    }

    @Override
    public  RunStateAndResult<ThreadLocalWhenInTest>
                processNewTestTask(NewTaskContext newTaskContext,
                                   Run run,
                                   SendEvent sendEvent) {
        return  RunStateAndResult.of(this, null);
    }

    @Override
    public RunStateAndResult<Boolean> checkBlocked(SendEvent sendEvent) {
        if(runStateContext.isBlocked(sendEvent)) {
            return new RunStateAndResult<>(new RunStateActive(runStateContext.withoutCalculated()),true);
        }
        return new RunStateAndResult<>(this,false);
    }

    @Override
    public RunStateContext runStateContext() {
        return runStateContext;
    }

    @Override
    public int startedThreadIndex() {
        return startedThreadIndex;
    }

    @Override
    protected TIntHashSet notYetWaitingThreadIndices() {
        return new TIntHashSet();
    }

    @Override
    protected RunState runStateWaiting(TIntHashSet newThreadIndices) {
        return new RunStateWaiting(runStateContext,startedThreadIndex,newThreadIndices);
    }

    @Override
    protected RunState runStateActive() {
        return this;
    }
}
