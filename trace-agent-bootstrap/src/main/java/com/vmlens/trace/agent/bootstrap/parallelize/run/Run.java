package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;


public interface Run {

    int runId();

    RuntimeEventAndWarnings after(RuntimeEvent runtimeEvent,
                                  ThreadLocalWhenInTest threadLocalDataWhenInTest);

    void startAtomicOperation(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest);

    void startAtomicOperationWithNewThread(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,
                                           RunnableOrThreadWrapper newThread);

    void newTask(RunnableOrThreadWrapper newWrapper, ThreadLocalForParallelize threadLocalForParallelize);

    ActualRun end(ThreadLocalForParallelize threadLocalForParallelize);


}
