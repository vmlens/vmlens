package com.vmlens.trace.agent.bootstrap.parallelize.run.impl.runstate;

public class RunStateAndChangeFlag {

    private final RunState nextState;
    private final boolean change;

    public RunStateAndChangeFlag(RunState nextState, boolean change) {
        this.nextState = nextState;
        this.change = change;
    }

    public RunState nextState() {
        return nextState;
    }

    public boolean change() {
        return change;
    }
}
