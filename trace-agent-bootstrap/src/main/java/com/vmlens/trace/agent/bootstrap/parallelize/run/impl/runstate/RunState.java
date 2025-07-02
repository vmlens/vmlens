package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.LockExitOrWaitEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.NewTaskContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.AfterContextForStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateAndResult;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public interface RunState {

    // reading
    boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,SendEvent sendEvent);

    // is null if end state
    ActualRun actualRun();

    // writing
    RunState  after(AfterContextForStateMachine afterContext, SendEvent sendEvent);

    RunState newTestTaskStarted(ThreadWrapper newWrapper);

    RunStateAndResult<ThreadLocalWhenInTest> processNewTestTask(NewTaskContext newTaskContext,
                                                                Run run,
                                                                SendEvent sendEvent);

    RunStateAndResult<Boolean> checkBlocked(SendEvent sendEvent);

    RunState waitCallOrBeforeLockExit(LockExitOrWaitEvent lockExitOrWaitEvent,
                                      ThreadLocalWhenInTest threadLocalDataWhenInTest,
                                      SendEvent sendEvent);


    RunState afterLockExitOrWait(ThreadLocalWhenInTest threadLocalDataWhenInTest);
}
