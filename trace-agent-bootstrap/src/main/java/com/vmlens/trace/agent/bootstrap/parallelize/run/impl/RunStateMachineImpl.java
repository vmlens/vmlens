package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.LockExitOrWaitEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.NewTaskContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate.RunStateActive;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate.RunStateEnd;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public class RunStateMachineImpl implements RunStateMachine  {

    private RunState currentState;

    public RunStateMachineImpl(RunStateContext runStateContext) {
        this.currentState = new RunStateActive(runStateContext);
    }

    @Override
    public void after(AfterContextForStateMachine afterContext, SendEvent sendEvent) {
        currentState = currentState.after(afterContext,sendEvent);
    }

    @Override
    public ThreadLocalWhenInTest processNewTestTask(NewTaskContext newTaskContext, Run run, SendEvent sendEvent) {
        RunStateAndResult<ThreadLocalWhenInTest> result = currentState.processNewTestTask(newTaskContext, run, sendEvent);
        currentState = result.runState();
        return result.result();
    }

    public void newTestTaskStarted(ThreadWrapper newWrapper) {
        currentState = currentState.newTestTaskStarted(newWrapper);
    }

    @Override
    public ActualRun end(ThreadLocalForParallelize threadLocalForParallelize) {
        threadLocalForParallelize.setParallelizedThreadLocalToNull();
        ActualRun actualRun = currentState.actualRun();
        currentState = new RunStateEnd();
        return actualRun;
    }

    @Override
    public boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,SendEvent sendEvent) {
        return currentState.isActive(threadLocalDataWhenInTest,sendEvent);
    }

    @Override
    public boolean checkStopWaiting(SendEvent sendEvent) {
        RunStateAndResult<Boolean> result = currentState.checkBlocked(sendEvent);
        currentState = result.runState();
        return result.result();
    }

    @Override
    public void beforeLockExitOrWait(LockExitOrWaitEvent lockExitOrWaitEvent,
                                     ThreadLocalWhenInTest threadLocalDataWhenInTest,
                                     SendEvent sendEvent) {
        currentState = currentState.waitCallOrBeforeLockExit(lockExitOrWaitEvent,threadLocalDataWhenInTest,sendEvent);
    }

    @Override
    public void afterLockExitOrWait(ThreadLocalWhenInTest threadLocalDataWhenInTest) {
        currentState = currentState.afterLockExitOrWait(threadLocalDataWhenInTest);
    }
}
