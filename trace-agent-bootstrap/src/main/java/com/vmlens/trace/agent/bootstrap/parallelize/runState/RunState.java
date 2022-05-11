package com.vmlens.trace.agent.bootstrap.parallelize.runState;

import com.vmlens.trace.agent.bootstrap.interleave.InterleaveFacade;
import com.vmlens.trace.agent.bootstrap.parallelize.command.Command;

public interface RunState {
    void after(InterleaveFacade interleaveFacade, Command command, long threadId);

    boolean needsToWait(long threadId);
}
