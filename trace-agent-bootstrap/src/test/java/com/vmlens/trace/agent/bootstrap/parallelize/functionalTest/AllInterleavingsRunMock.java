package com.vmlens.trace.agent.bootstrap.parallelize.functionalTest;

import com.vmlens.trace.agent.bootstrap.parallelize.command.Command;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.AllInterleavingsRun;
import com.vmlens.trace.agent.bootstrap.parallelize.loop.RunStateMachine;

public class AllInterleavingsRunMock implements AllInterleavingsRun {

    private final RunStateMachine runState = new RunStateMachine();

    @Override
    public void close() {

    }

    @Override
    public void after(Command command, long threadId) {

    }

    @Override
    public boolean advance() {
        return false;
    }

    public RunStateMachine getRunState() {
        return runState;
    }
}
