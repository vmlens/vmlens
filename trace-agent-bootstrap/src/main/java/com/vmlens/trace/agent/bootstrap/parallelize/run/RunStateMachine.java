package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.parallize.logic.RunnableOrThreadWrapper;

public interface RunStateMachine {
    boolean isActive(TestThreadState testThreadState);
    void after(ParallelizeAction action, TestThreadState testThreadState);
    boolean isNewTestTask(RunnableOrThreadWrapper newWrapper);
    void processNewTestTask(TestThreadState testThreadState);
    void end();
}
