package com.vmlens.trace.agent.bootstrap.parallelize.run;


import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;

public interface RunStateMachine {

    RuntimeEvent after(RuntimeEvent runtimeEvent, ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest);

    ThreadLocalWhenInTestAndSerializableEvents processNewTestTask(RunnableOrThreadWrapper newWrapper,
                                             ThreadLocalForParallelize threadLocalForParallelize,
                                             Run run);

    boolean canProcessEndOfOperation(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest);


    void startAtomicOperation(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest);

    void startAtomicOperationWithNewThread(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,
                                           RunnableOrThreadWrapper newThread);

    boolean canStartAtomicOperation();

    RuntimeEvent endAtomicOperation(RuntimeEvent runtimeEvent,
                                    ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest);

    ActualRun end(ThreadLocalForParallelize threadLocalForParallelize);


}
