package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;

public interface RunStateMachineFactory {
    RunStateMachine create(ActualRun calculatedRun, TestThreadState testThreadState);
}
