package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;


public interface RunStateMachineFactory {
    RunStateMachine createRunning(int loopId, int runId,
                                  CalculatedRun calculatedRun, ActualRun actualRun);

    RunStateMachine createInitial(int loopId, int runId, ActualRun actualRun);
}
