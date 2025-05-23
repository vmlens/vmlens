package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate;


import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.AfterContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.NewTaskContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateAndResult;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

import static com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate.ProcessAfter.process;

public class RunStateActive implements RunState {

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

    @Override
    public boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,SendEvent sendEvent) {
        return runStateContext.isActive(threadLocalDataWhenInTest.threadIndex(),sendEvent);
    }

    @Override
    public ActualRun actualRun() {
        return runStateContext.actualRun();
    }

    @Override
    public RunState after(AfterContext afterContext, SendEvent sendEvent) {
        process(afterContext, sendEvent, runStateContext, startedThreadIndex);
        return this;
    }

    @Override
    public RunState newTestTaskStarted(RunnableOrThreadWrapper startedThread) {
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

}
