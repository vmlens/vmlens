package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingorder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.context.InterleaveLoopContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.impl.ThreadIndexAndThreadStateMap;


public interface RunStateMachineFactory {
    RunStateMachine createRunning(InterleaveLoopContext interleaveLoopContext,
                                  ThreadIndexAndThreadStateMap runContext,
                                  CalculatedRun calculatedRun);

    RunStateMachine createInitial(InterleaveLoopContext interleaveLoopContext,
                                  ThreadIndexAndThreadStateMap runContext);
}
