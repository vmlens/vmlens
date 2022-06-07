package com.vmlens.trace.agent.bootstrap.parallelize.runState;

import com.vmlens.trace.agent.bootstrap.parallelize.command.ParallelizeCommand;

public interface RunState {
    RunState after(ParallelizeCommand parallelizeCommand);

    RunState timeout();

    boolean needsToWait(long threadId);

}
