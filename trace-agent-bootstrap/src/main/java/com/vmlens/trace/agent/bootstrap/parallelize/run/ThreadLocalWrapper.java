package com.vmlens.trace.agent.bootstrap.parallelize.run;

public interface ThreadLocalWrapper {
    long threadId();
    void setParallelizedThreadLocal(ParallelizedThreadLocal parallelizedThreadLocal);
    ParallelizedThreadLocal getParallelizedThreadLocal();
}
