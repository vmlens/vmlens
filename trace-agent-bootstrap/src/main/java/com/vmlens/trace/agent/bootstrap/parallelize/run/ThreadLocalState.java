package com.vmlens.trace.agent.bootstrap.parallelize.run;

public interface ThreadLocalState {

    void createNewParallelizedThreadLocal(Run run);

    void setParallelizedThreadLocalToNull();

}
