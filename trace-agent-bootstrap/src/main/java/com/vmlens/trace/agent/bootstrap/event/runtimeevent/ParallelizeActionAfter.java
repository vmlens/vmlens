package com.vmlens.trace.agent.bootstrap.event.runtimeevent;

import com.vmlens.trace.agent.bootstrap.event.PerThreadCounter;
import com.vmlens.trace.agent.bootstrap.interleave.run.InterleaveRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.SendEvent;
import com.vmlens.trace.agent.bootstrap.parallelize.run.thread.ThreadLocalWhenInTestForParallelize;

public interface ParallelizeActionAfter {

    void setStartedThreadIndex(int startedThreadIndex);

    void after(InterleaveRun interleaveRun,
               CreateInterleaveActionContext context,
               ThreadLocalWhenInTestForParallelize threadLocalWhenInTestForParallelize,
               SendEvent sendEvent);




}
