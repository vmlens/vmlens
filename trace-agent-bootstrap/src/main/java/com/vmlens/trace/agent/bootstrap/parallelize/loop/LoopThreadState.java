package com.vmlens.trace.agent.bootstrap.parallelize.loop;

import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;

public interface LoopThreadState {

    void createNewParallelizedThreadLocal(Run run);
    void setParallelizedThreadLocalToNull();

}
