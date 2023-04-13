package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunContext;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunState;

public class RunStateRunning implements RunState, RunContext {
    private final RunStateContext runStateContext;

    @Override
    public CalculatedRun getCalculatedRun() {
        return runStateContext.calculatedRun;
    }

    @Override
    public int threadIndexForThreadId(long id) {
        return (int) id;
    }

    @Override
    public RunState current() {
        return this;
    }

    public RunStateRunning(CalculatedRun calculatedRun) {
        this.runStateContext = new RunStateContext(new ThreadIdToState(),calculatedRun);
    }

    @Override
    public boolean isActive(long threadId) {
        return false;
    }

    @Override
    public void after(ParallelizeAction action) {
        action.addSyncAction(this);
    }

    @Override
    public RunState newThread(Thread newThread) {
        return this;
    }


    @Override
    public RunState prepare(ParallelizeAction action) {
        return action.prepare(this);
    }
}
