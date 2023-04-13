package com.vmlens.trace.agent.bootstrap.parallelize.run;

public interface RunStateMachine {
    boolean isActive(long threadId);
    void after(ParallelizeAction action);
    boolean newThread(Thread newThread);
    void end();
}
