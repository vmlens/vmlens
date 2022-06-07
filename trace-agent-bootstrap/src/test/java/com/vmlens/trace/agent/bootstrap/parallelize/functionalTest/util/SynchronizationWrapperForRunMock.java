package com.vmlens.trace.agent.bootstrap.parallelize.functionalTest.util;

import com.vmlens.trace.agent.bootstrap.parallelize.command.ParallelizeCommand;
import com.vmlens.trace.agent.bootstrap.parallelize.command.RunStateContext;
import com.vmlens.trace.agent.bootstrap.parallelize.runAndLoop.AllInterleavingsLoop;
import com.vmlens.trace.agent.bootstrap.parallelize.runAndLoop.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.runAndLoop.SynchronizationWrapperForRun;
import com.vmlens.trace.agent.bootstrap.parallelize.runState.RunStateContextImpl;
import com.vmlens.trace.agent.bootstrap.parallelize.runState.ThreadIdToState;

public class SynchronizationWrapperForRunMock implements SynchronizationWrapperForRun {

    private final AllInterleavingsLoop loop;
    private final ThreadIdToState threadIdToState;
    private final RunStateMachine runState;

    public SynchronizationWrapperForRunMock(AllInterleavingsLoop loop) {
        this.loop = loop;
        this.threadIdToState = new ThreadIdToState();
        RunStateContext runStateContext = new RunStateContextImpl(loop.getInterleaveFacade(), threadIdToState);
        runState = new RunStateMachine(runStateContext);
    }

    @Override
    public void close() {
        runState.close();
    }

    @Override
    public void after(ParallelizeCommand parallelizeCommand) {
        runState.after(parallelizeCommand);
    }

    public RunStateMachine getRunState() {
        return runState;
    }

    public ThreadIdToState getThreadIdToState() {
        return threadIdToState;
    }
}
