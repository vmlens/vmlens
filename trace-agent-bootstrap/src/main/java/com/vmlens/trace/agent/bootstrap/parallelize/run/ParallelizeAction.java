package com.vmlens.trace.agent.bootstrap.parallelize.run;

public interface ParallelizeAction {
    RunState prepare(RunContext context);
    void addSyncAction(RunContext context);
}
