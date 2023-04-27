package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRun;

public interface RunStateMachineFactory {
    RunStateMachine create(InterleaveRun calculatedRun, TestThreadState testThreadState);
}
