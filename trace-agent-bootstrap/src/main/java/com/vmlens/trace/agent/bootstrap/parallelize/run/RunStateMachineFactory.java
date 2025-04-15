package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.run.ActualRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;


public interface RunStateMachineFactory {
    RunStateMachine createRunning(ThreadIndexAndThreadStateMap runContext,
                                  CalculatedRun calculatedRun);

    RunStateMachine createInitial(ThreadIndexAndThreadStateMap runContext);
}
