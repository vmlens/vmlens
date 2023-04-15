package com.vmlens.trace.agent.bootstrap.parallelize.run;

public interface Run {

    void after(ParallelizeAction action);

    void end(ThreadLocalState threadLocalState);

    void newThread(Thread newThread, ThreadLocalState threadLocalState);
}
