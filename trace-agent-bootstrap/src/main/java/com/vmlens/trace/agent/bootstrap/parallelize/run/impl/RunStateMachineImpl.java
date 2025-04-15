package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.*;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate.RunStateActive;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate.RunStateEnd;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestAndSerializableEvents;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public class RunStateMachineImpl implements RunStateMachine  {

    private RunState currentState;

    public RunStateMachineImpl(RunStateContext runStateContext) {
        this.currentState = new RunStateActive(runStateContext);
    }

    @Override
    public void after(AfterContext afterContext, SendEvent sendEvent) {
        currentState = currentState.after(afterContext,sendEvent);
    }

    @Override
    public ThreadLocalWhenInTest processNewTestTask(NewTaskContext newTaskContext, Run run, SendEvent sendEvent) {
        RunStateAndResult<ThreadLocalWhenInTest> result = currentState.processNewTestTask(newTaskContext, run, sendEvent);
        currentState = result.runState();
        return result.result();
    }

    public void newTestTaskStarted(RunnableOrThreadWrapper newWrapper) {
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
    public boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest) {
        return currentState.isActive(threadLocalDataWhenInTest);
    }

    @Override
    public boolean checkStopWaiting() {
        RunStateAndResult<Boolean> result = currentState.checkBlocked();
        currentState = result.runState();
        return result.result();
    }
}
