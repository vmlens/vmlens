package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadLocalDataWhenInTestMap;


public interface RunStateMachineFactory {
    RunStateMachine createRunning(ThreadLocalDataWhenInTestMap runContext,
                                  CalculatedRun calculatedRun, ActualRun actualRun);

    RunStateMachine createInitial(ThreadLocalDataWhenInTestMap runContext, ActualRun actualRun);
}
