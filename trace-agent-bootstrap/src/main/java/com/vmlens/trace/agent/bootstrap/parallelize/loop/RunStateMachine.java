package com.vmlens.trace.agent.bootstrap.parallelize.loop;

/*
 entsprechend waitnotifyhandler +
 needsToWait

 */

import com.vmlens.trace.agent.bootstrap.interleave.InterleaveFacade;
import com.vmlens.trace.agent.bootstrap.parallelize.command.Command;
import com.vmlens.trace.agent.bootstrap.parallelize.runState.RunState;
import com.vmlens.trace.agent.bootstrap.parallelize.runState.RunStateRun;

public class RunStateMachine {

    private final InterleaveFacade interleaveFacade = new InterleaveFacade();
    private RunState state = new RunStateRun();

    boolean advance() {
        return interleaveFacade.advance();
    }

    void close() {
    }

    void after(Command command, long threadId) {
    }

    public boolean needsToWait(long threadId) {
        return false;
    }

    void timeout() {
    }

}
