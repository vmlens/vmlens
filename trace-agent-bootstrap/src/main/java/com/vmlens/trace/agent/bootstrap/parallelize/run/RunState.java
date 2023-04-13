package com.vmlens.trace.agent.bootstrap.parallelize.run;

public interface RunState {
    boolean isActive(long threadId);
    void after(ParallelizeAction action);
    RunState prepare(ParallelizeAction action);
    RunState newThread(Thread newThread);
}
