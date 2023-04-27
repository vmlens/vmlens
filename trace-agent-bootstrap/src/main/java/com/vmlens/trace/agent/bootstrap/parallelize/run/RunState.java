package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRun;
import com.vmlens.trace.agent.bootstrap.parallize.logic.RunnableOrThreadWrapper;

public interface RunState {
    boolean isActive(TestThreadState testThreadState);
    RunState after(ParallelizeAction action, TestThreadState testThreadState);
    boolean isNewTestTask(RunnableOrThreadWrapper newWrapper);
    void addTaskStartedInterleaveAction(TestThreadState beginTestThreadState, InterleaveRun calculatedRun);
}
