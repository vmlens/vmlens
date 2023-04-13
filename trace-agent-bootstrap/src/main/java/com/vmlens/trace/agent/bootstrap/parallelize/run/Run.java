package com.vmlens.trace.agent.bootstrap.parallelize.run;

public interface Run {

    void after(ParallelizeAction action);
    void end();
    boolean newThread(Thread newThread);
}
