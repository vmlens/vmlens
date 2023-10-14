package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.event.SendEventContext;

public interface ThreadLocalWrapper extends SendEventContext {
    long threadId();

    void setParallelizedThreadLocal(ParallelizedThreadLocal parallelizedThreadLocal);

    ParallelizedThreadLocal getParallelizedThreadLocal();
}
