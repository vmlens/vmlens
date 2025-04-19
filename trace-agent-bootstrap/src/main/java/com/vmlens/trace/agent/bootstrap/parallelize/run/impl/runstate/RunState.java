package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate;

import com.vmlens.trace.agent.bootstrap.callback.threadlocal.ThreadLocalWhenInTest;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.RunnableOrThreadWrapper;
import com.vmlens.trace.agent.bootstrap.callback.callbackaction.AfterContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.NewTaskContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.RunStateAndResult;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public interface RunState {

    // reading

    boolean isActive(ThreadLocalWhenInTestForParallelize threadLocalDataWhenInTest);

    // is null if end state
    ActualRun actualRun();

    // writing

    RunState  after(AfterContext afterContext, SendEvent sendEvent);

    RunState newTestTaskStarted(RunnableOrThreadWrapper newWrapper);

    RunStateAndResult<ThreadLocalWhenInTest> processNewTestTask(NewTaskContext newTaskContext,
                                                                Run run,
                                                                SendEvent sendEvent);

    RunStateAndResult<Boolean> checkBlocked();

}
