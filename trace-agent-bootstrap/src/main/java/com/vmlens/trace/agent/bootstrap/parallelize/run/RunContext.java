package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;

public interface RunContext {
    CalculatedRun getCalculatedRun();
    int threadIndexForThreadId(long id);
    RunState current();
}
