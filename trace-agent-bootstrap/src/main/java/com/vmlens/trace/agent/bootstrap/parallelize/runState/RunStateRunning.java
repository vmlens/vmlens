package com.vmlens.trace.agent.bootstrap.parallelize.runState;

import com.vmlens.trace.agent.bootstrap.parallelize.command.ParallelizeCommand;
import com.vmlens.trace.agent.bootstrap.parallelize.command.RunStateContext;

public class RunStateRunning implements RunState {

    private final RunStateContext runStateContext;

    public RunStateRunning(RunStateContext runStateContext) {
        this.runStateContext = runStateContext;
    }

    @Override
    public RunState after(ParallelizeCommand parallelizeCommand) {
        parallelizeCommand.execute(runStateContext);
        return this;
    }

    @Override
    public RunState timeout() {
        return null;
    }

    @Override
    public boolean needsToWait(long threadId) {
        return runStateContext.needsToWait(threadId);
    }

}
