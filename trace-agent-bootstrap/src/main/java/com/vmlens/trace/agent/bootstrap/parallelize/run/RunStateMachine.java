package com.vmlens.trace.agent.bootstrap.parallelize.run;


import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.runtimeevent.LockExitOrWaitEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.ThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.AfterContextForStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public interface RunStateMachine {

    // Reading
    boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,SendEvent sendEvent);

    boolean checkStopWaiting(SendEvent sendEvent);

    // Writing
    void after(AfterContextForStateMachine afterContext,
               SendEvent sendEvent);

    void newTestTaskStarted(ThreadWrapper newWrapper);

    // can be null when this is not a test task
    ThreadLocalWhenInTest processNewTestTask(NewTaskContext newTaskContext,
                                             Run run,
                                             SendEvent sendEvent);

    // can be null when the run is already ended
    ActualRun end(ThreadLocalForParallelize threadLocalForParallelize);

    void beforeLockExitOrWait(LockExitOrWaitEvent lockExitOrWaitEvent,
                              ThreadLocalWhenInTest threadLocalDataWhenInTest,
                              SendEvent sendEvent);

    void afterLockExitOrWait(ThreadLocalWhenInTest threadLocalDataWhenInTest);

}
