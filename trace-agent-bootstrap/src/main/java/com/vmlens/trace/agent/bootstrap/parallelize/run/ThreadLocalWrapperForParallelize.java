package com.vmlens.trace.agent.bootstrap.parallelize.run;

import com.vmlens.trace.agent.bootstrap.event.ThreadLocalWrapperForEvent;

public interface ThreadLocalWrapperForParallelize extends ThreadLocalWrapperForEvent {


    void setParallelizedThreadLocal(ParallelizedThreadLocal parallelizedThreadLocal);

    ParallelizedThreadLocal getParallelizedThreadLocal();


}
