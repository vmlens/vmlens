package com.vmlens.trace.agent.bootstrap.interleave.run;

public class StateAndThreadIndex {

    private final InterleaveRunState state;
    private final int threadIndex;

    public StateAndThreadIndex(InterleaveRunState state, int threadIndex) {
        this.state = state;
        this.threadIndex = threadIndex;
    }

    public int threadIndex() {
        return threadIndex;
    }

    public InterleaveRunState state() {
        return state;
    }
}
