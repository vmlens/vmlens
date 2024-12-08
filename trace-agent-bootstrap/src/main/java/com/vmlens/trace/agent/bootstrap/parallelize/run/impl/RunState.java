package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.event.RuntimeEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalForParallelize;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalWhenInTestForParallelize;

public interface RunState {

    boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest);

    RunStateAndResult<ThreadLocalWhenInTest> processNewTestTask(RunnableOrThreadWrapper newWrapper,
                                                                ThreadLocalForParallelize threadLocalForParallelize,
                                                                Run run);

    RunState startAtomicOperation(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest);

    RunState startAtomicOperationWithNewThread(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest,
                                               RunnableOrThreadWrapper newThread,
                                               ThreadLocalDataWhenInTestMap runContext);

    RunStateAndResult<RuntimeEvent> endAtomicOperation(ProcessRuntimeEventCallback processRuntimeEventCallback,
                                                       RuntimeEvent runtimeEvent,
                                                       ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest);

    RunStateAndResult<RuntimeEvent> after(ProcessRuntimeEventCallback processRuntimeEventCallback,
                                          RuntimeEvent runtimeEvent,
                                          ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest);

    boolean isStartAtomicOperationPossible();
}
