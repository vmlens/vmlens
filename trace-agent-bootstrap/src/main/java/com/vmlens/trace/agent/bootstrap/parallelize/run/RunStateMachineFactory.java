package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.interleave.alternatingOrder.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.interleave.loop.InterleaveLoop;


public interface RunStateMachineFactory {
    RunStateMachine createRunning(CalculatedRun calculatedRun, TestThreadState testThreadState, InterleaveLoop interleaveLoop);

    RunStateMachine createInitial(TestThreadState testThreadState, InterleaveLoop interleaveLoop);
}
