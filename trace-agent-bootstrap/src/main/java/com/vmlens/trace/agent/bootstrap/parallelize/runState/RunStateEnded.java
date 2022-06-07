package com.vmlens.trace.agent.bootstrap.parallelize.runState;

import com.vmlens.trace.agent.bootstrap.parallelize.command.ParallelizeCommand;

public class RunStateEnded implements RunState {
    @Override
    public RunState after(ParallelizeCommand parallelizeCommand) {
        return this;
    }

    @Override
    public RunState timeout() {
        return this;
    }

    @Override
    public boolean needsToWait(long threadId) {
        return false;
    }
}
