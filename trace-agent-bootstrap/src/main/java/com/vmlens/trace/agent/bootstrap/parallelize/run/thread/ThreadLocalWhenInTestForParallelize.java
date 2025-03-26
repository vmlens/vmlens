package com.vmlens.trace.agent.bootstrap.parallelize.run.thread;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;

public interface ThreadLocalWhenInTestForParallelize extends PerThreadCounter  {

    int threadIndex();


}
