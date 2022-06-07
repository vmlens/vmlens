package com.vmlens.trace.agent.bootstrap.parallelize.runAndLoop;

/*
 entsprechend waitnotifyhandler +
 needsToWait

 */

import com.vmlens.trace.agent.bootstrap.parallelize.command.ParallelizeCommand;
import com.vmlens.trace.agent.bootstrap.parallelize.command.RunStateContext;
import com.vmlens.trace.agent.bootstrap.parallelize.runState.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.runState.RunStateEnded;
import com.vmlens.trace.agent.bootstrap.parallelize.runState.RunStateRunning;


public class RunStateMachine {

    private RunState state;

    public RunStateMachine(RunStateContext runStateContext) {
        this.state = new RunStateRunning(runStateContext);
    }

    public void close() {
        state = new RunStateEnded();
    }

    public void after(ParallelizeCommand parallelizeCommand) {
        state = state.after(parallelizeCommand);
    }

    void timeout() {
        state = state.timeout();
    }

    public boolean needsToWait(long threadId) {
        return state.needsToWait(threadId);
    }


}
