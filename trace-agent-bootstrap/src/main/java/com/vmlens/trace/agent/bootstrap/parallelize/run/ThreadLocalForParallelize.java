package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.event.QueueIn;

public interface ThreadLocalForParallelize {


    ThreadLocalDataWhenInTest getThreadLocalDataWhenInTest();

    void setThreadLocalDataWhenInTest(ThreadLocalDataWhenInTest parallelizedThreadLocal);

    ThreadLocalDataWhenInTest startCallbackProcessing();

    QueueIn queueIn();

    long threadId();

    void setParallelizedThreadLocalToNull();

    ThreadLocalDataWhenInTest createNewParallelizedThreadLocal(Run run, int maxThreadIndex);
}
