package com.vmlens.trace.agent.bootstrap.parallelize.run;


import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public interface RunStateMachine {

    boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest);

    RuntimeEvent after(RuntimeEvent runtimeEvent, ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest);

    ThreadLocalWhenInTest processNewTestTask(RunnableOrThreadWrapper newWrapper,
                                             ThreadLocalForParallelize threadLocalForParallelize,
                                             Run run);

    void startAtomicOperation(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest);

    void startAtomicOperationWithNewThread(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,
                                           RunnableOrThreadWrapper newThread);

    RuntimeEvent endAtomicOperation(RuntimeEvent runtimeEvent,
                                    ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest);

    ActualRun end(ThreadLocalForParallelize threadLocalForParallelize);

    boolean isStartAtomicOperationPossible();

}
