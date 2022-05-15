package com.vmlens.trace.agent.bootstrap.parallelize.runState;

import com.vmlens.trace.agent.bootstrap.interleave.InterleaveFacade;
import com.vmlens.trace.agent.bootstrap.parallelize.command.Command;

public class RunStateRun implements RunState {
    @Override
    public void after(InterleaveFacade interleaveFacade, Command command, long threadId) {

    }

    @Override
    public boolean needsToWait(long threadId) {
        return false;
    }
}
