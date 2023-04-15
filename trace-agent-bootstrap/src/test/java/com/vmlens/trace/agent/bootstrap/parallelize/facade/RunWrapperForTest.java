package com.vmlens.trace.agent.bootstrap.parallelize.facade;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ParallelizeAction;
import com.vmlens.trace.agent.bootstrap.parallelize.run.Run;
import com.vmlens.trace.agent.bootstrap.parallelize.run.RunStateMachine;
import com.vmlens.trace.agent.bootstrap.parallelize.run.ThreadLocalState;
import com.vmlens.trace.agent.bootstrap.parallelize.runImpl.RunStateMachineImpl;

public class RunWrapperForTest implements Run {
    private final RunStateMachine runStateMachine;

    public RunWrapperForTest(CalculatedRun calculatedRun) {
        this.runStateMachine = new RunStateMachineImpl(calculatedRun);
    }

    @Override
    public void after(ParallelizeAction action) {
        runStateMachine.after(action);
    }

    @Override
    public void end(ThreadLocalState threadLocalState) {

    }

    @Override
    public void newThread(Thread newThread, ThreadLocalState threadLocalState) {
        threadLocalState.createNewParallelizedThreadLocal(this);
    }
}
