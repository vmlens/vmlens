package com.vmlens.trace.agent.bootstrap.parallelize.runImpl;

import com.vmlens.trace.agent.bootstrap.interleave.calculatedRun.CalculatedRun;

public class RunStateContext {
    public final ThreadIdToState threadIdToState;
    public final CalculatedRun calculatedRun;

    public RunStateContext(ThreadIdToState threadIdToState, CalculatedRun calculatedRun) {
        this.threadIdToState = threadIdToState;
        this.calculatedRun = calculatedRun;
    }
}
