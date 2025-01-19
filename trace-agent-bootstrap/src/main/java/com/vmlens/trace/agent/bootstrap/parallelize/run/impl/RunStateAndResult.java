package com.vmlens.trace.agent.bootstrap.parallelize.run.impl;


public class RunStateAndResult<RESULT> {

    private final RunState runState;
    private final RESULT result;

    public RunStateAndResult(RunState runState, RESULT result) {
        this.runState = runState;
        this.result = result;
    }

    public static <RESULT> RunStateAndResult<RESULT> of(RunState runState, RESULT result) {
        return new RunStateAndResult<>(runState, result);
    }

    public RunState runState() {
        return runState;
    }

    public RESULT result() {
        return result;
    }
}
