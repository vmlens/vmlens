package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.ExecuteBeforeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.NewTaskContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.AfterContextForStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateAndResult;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

/**
 * null object design pattern
 */
public class RunStateEnd implements RunState {

    @Override
    public boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,SendEvent sendEvent) {
        return true;
    }

    @Override
    public RunState after(AfterContextForStateMachine afterContext, SendEvent sendEvent) {
        return this;
    }

    @Override
    public  RunStateAndResult<ThreadLocalWhenInTest> processNewTestTask(NewTaskContext newTaskContext,
                       Run run,
                       SendEvent sendEvent) {
        return RunStateAndResult.of(this, null);
    }

    @Override
    public RunStateAndResult<Boolean> checkBlocked(SendEvent sendEvent,int waitingThreadIndex) {
        return new RunStateAndResult<>(this,false);
    }

    @Override
    public RunState beforeLockExitWaitOrThreadStart(ExecuteBeforeEvent lockExitOrWaitEvent, ThreadLocalWhenInTest threadLocalDataWhenInTest, SendEvent sendEvent) {
        return this;
    }

    @Override
    public RunState afterLockExitWaitOrThreadStart(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        return this;
    }

    @Override
    public ActualRun actualRun() {
        return null;
    }

    @Override
    public boolean isEnded() {
        return true;
    }
}
