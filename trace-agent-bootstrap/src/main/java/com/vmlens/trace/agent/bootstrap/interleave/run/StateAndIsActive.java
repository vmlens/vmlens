package com.vmlens.trace.agent.bootstrap.interleave.run;

public class StateAndIsActive {

    private final InterleaveRunState state;
    private final boolean isActive;

    public StateAndIsActive(InterleaveRunState state, boolean isActive) {
        this.state = state;
        this.isActive = isActive;
    }

    public InterleaveRunState state() {
        return state;
    }

    public boolean isActive() {
        return isActive;
    }
}
