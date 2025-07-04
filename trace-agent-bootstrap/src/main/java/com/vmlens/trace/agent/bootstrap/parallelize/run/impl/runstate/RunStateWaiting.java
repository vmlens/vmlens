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
import static com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate.RunStateActive.calculateIsActive;

public class RunStateWaiting extends ProcessLockExitOrWaitTemplate implements RunState {

    private TIntHashSet notYetWaitingThreadIndices;
    private final RunStateContext runStateContext;
    private final int startedThreadIndex;

    public RunStateWaiting(RunStateContext runStateContext,
                           int startedThreadIndex,
                           TIntHashSet notYetWaitingThreadIndices) {
        this.runStateContext = runStateContext;
        this.startedThreadIndex = startedThreadIndex;
        this.notYetWaitingThreadIndices = notYetWaitingThreadIndices;
    }

    @Override
    public boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest, SendEvent sendEvent) {
        notYetWaitingThreadIndices = runStateContext.removeNotActive(notYetWaitingThreadIndices);
        if(! notYetWaitingThreadIndices.isEmpty()) {
            return false;
        }

        return calculateIsActive(runStateContext,threadLocalDataWhenInTest,sendEvent);
    }

    @Override
    public ActualRun actualRun() {
        return runStateContext.actualRun();
    }

    @Override
    public RunState after(AfterContextForStateMachine afterContext, SendEvent sendEvent) {
        process(afterContext, sendEvent, runStateContext, startedThreadIndex);
        notYetWaitingThreadIndices = runStateContext.removeNotActive(notYetWaitingThreadIndices);
        if(! notYetWaitingThreadIndices.isEmpty()) {
            return this;
        }
        return new RunStateActive(runStateContext,startedThreadIndex);
    }

    @Override
    public RunState newTestTaskStarted(ThreadWrapper startedThread) {
        int threadIndexForNewTestTask = runStateContext.getThreadIndexForNewTestThread();
        return new RunStateNewThreadStarted(runStateContext, startedThread,threadIndexForNewTestTask);
    }

    @Override
    public RunStateAndResult<ThreadLocalWhenInTest> processNewTestTask(NewTaskContext newTaskContext, Run run, SendEvent sendEvent) {
        return RunStateAndResult.of(this, null);
    }

    @Override
    public RunStateAndResult<Boolean> checkBlocked(SendEvent sendEvent) {
        notYetWaitingThreadIndices = runStateContext.removeNotActive(notYetWaitingThreadIndices);
        if(! notYetWaitingThreadIndices.isEmpty()) {
            return new RunStateAndResult<>(this,false);
        }
        return  new RunStateAndResult<>(new RunStateActive(runStateContext,startedThreadIndex),false);
    }

    @Override
    public TIntHashSet notYetWaitingThreadIndices() {
        return notYetWaitingThreadIndices;
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
    protected RunState runStateWaiting(TIntHashSet newThreadIndices) {
        this.notYetWaitingThreadIndices = newThreadIndices;
        return this;
    }

    @Override
    protected RunState runStateActive() {
        return new RunStateActive(runStateContext,startedThreadIndex);
    }
}
