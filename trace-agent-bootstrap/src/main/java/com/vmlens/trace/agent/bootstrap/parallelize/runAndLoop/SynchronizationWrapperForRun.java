package com.vmlens.trace.agent.bootstrap.parallelize.runAndLoop;

import com.vmlens.trace.agent.bootstrap.parallelize.command.ParallelizeCommand;

public interface SynchronizationWrapperForRun {
    void close();

    void after(ParallelizeCommand parallelizeCommand);

}
